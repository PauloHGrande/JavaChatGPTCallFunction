package com.example.chatgptoracle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ChatGptService(
            @Value("${openai.api.key}") String apiKey,
            @Value("${openai.api.url}") String apiUrl
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl) // 👉 DEVE ser https://api.openai.com/v1
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 👈 datas legíveis
    }

    /**
     * Primeiro function calling: decidir tabela/filtro/tipo
     */
    public Mono<FunctionCall> askGptFunctionCalling(String question) {
        String body = String.format("""
            {
              "model": "gpt-4.1-mini",
              "messages": [{"role": "user", "content": "%s"}],
              "functions": [
                {
                  "name": "consultar_banco",
                  "description": "Consulta tabelas no Oracle",
                  "parameters": {
                    "type": "object",
                    "properties": {
                      "tabela": {
                        "type": "string",
                        "enum": ["produto", "cliente", "pedido", "faturas"],
                        "description": "Escolha apenas uma destas tabelas"
                      },
                      "filtro": {
                        "type": "string",
                        "description": "Nome do cliente ou produto relacionado ao pedido, ex: 'Ana Souza' ou 'Notebook Dell'"
                      },
                      "tipo": {
                        "type": "string",
                        "enum": ["cliente", "produto"],
                        "description": "Quando a tabela for 'pedido', indique se o filtro é um cliente ou produto"
                      }
                    },
                    "required": ["tabela"]
                  }
                }
              ],
              "function_call": "auto"
            }
            """, question);

        return webClient.post()
                .uri("/chat/completions") // 👈 evita duplicação
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        JsonNode root = objectMapper.readTree(response);
                        JsonNode functionCallNode = root
                                .path("choices").get(0)
                                .path("message")
                                .path("function_call");

                        // Log para debug
                        System.out.println("Resposta GPT (function_call): " + functionCallNode.toPrettyString());

                        String args = functionCallNode.path("arguments").asText();
                        return objectMapper.readValue(args, FunctionCall.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao processar resposta do GPT", e);
                    }
                });
    }

    /**
     * Segundo function calling: formatar resposta em linguagem natural
     */
    public <T> Mono<String> askGptFormatAnswer(String question, List<T> dados) {
        String jsonDados;
        try {
            jsonDados = objectMapper.writeValueAsString(dados);
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }

        // Define a função que o GPT deve "chamar"
        Map<String, Object> function = Map.of(
                "name", "formatar_resposta",
                "description", "Gera uma resposta em linguagem natural para a pergunta com base nos dados fornecidos.",
                "parameters", Map.of(
                        "type", "object",
                        "properties", Map.of(
                                "resposta", Map.of(
                                        "type", "string",
                                        "description", "Resposta em português baseada nos dados recebidos."
                                )
                        ),
                        "required", List.of("resposta")
                )
        );

        Map<String, Object> body = Map.of(
                "model", "gpt-4.1-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um especialista em SQL.---Retorne as informações em HTML, utilizando listas, <strong>, <em> e tabelas quando necessário.---Você pode utilizar os componentes do Bootstrap na sua resposta, incluindo suas classes CSS.---Quando retornar uma <table> você deve utilizar nela as classes do Bootstrap: \"table table-striped table-bordered\".---Gere sempre respostas para pessoas, nunca responda com SQL (A não ser que o usuário peça especificamente por SQL)."),
                        Map.of("role", "user", "content",
                                "Por favor, gere um objeto JSON válido para a função 'formatar_resposta' com a chave 'resposta'. " +
                                        "Pergunta: " + question + "\nDados: " + jsonDados)
                ),
                "functions", List.of(function),
                "function_call", Map.of("name", "formatar_resposta")
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    try {
                        // Pega o function_call.arguments
                        String argsStr = json.path("choices")
                                .get(0)
                                .path("message")
                                .path("function_call")
                                .path("arguments")
                                .asText();

                        // Converte para JSON e pega a resposta
                        JsonNode argsJson = objectMapper.readTree(argsStr);
                        String resposta = argsJson.path("resposta").asText();

                        System.out.println("Resposta GPT (formatar_resposta): " + resposta);
                        return resposta;
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Erro ao processar resposta formatada do GPT", e);
                    }
                });
    }

    /**
     * DTO para o primeiro function_call
     */
    public static class FunctionCall {
        public String tabela;
        public String filtro;
        public String tipo;
    }
}