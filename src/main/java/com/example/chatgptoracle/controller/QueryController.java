package com.example.chatgptoracle.controller;

import com.example.chatgptoracle.service.ChatGptService;
import com.example.chatgptoracle.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.*;

@RestController
public class QueryController {

    private final ChatGptService chatGptService;
    private final DatabaseService databaseService;

    public QueryController(ChatGptService chatGptService, DatabaseService databaseService) {
        this.chatGptService = chatGptService;
        this.databaseService = databaseService;
    }

    @Operation(summary = "Consulta tabelas usando ChatGPT Function-Calling",
            description = "O ChatGPT decide qual tabela consultar (produto, cliente, pedido, faturas) e retorna os dados")
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @GetMapping("/query")
    public Mono<Object> query(
            @Parameter(description = "Pergunta que o ChatGPT deve interpretar")
            @RequestParam String question) {

        return chatGptService.askGptFunctionCalling(question)
                .flatMap(functionCall -> {
                    String tabela = functionCall.tabela.toLowerCase();
                    String filtro = functionCall.filtro;
                    String tipo = functionCall.tipo;

                    Mono<List<Map<String, Object>>> dados;

                    switch (tabela) {
                        case "produto" -> dados = Mono.fromCallable(() ->
                                        databaseService.queryGenerica("SELECT * FROM PRODUTO_PAULO"))
                                .subscribeOn(Schedulers.boundedElastic());

                        case "cliente" -> dados = Mono.fromCallable(() ->
                                        databaseService.queryGenerica("SELECT * FROM CLIENTE_PAULO"))
                                .subscribeOn(Schedulers.boundedElastic());

                        case "faturas" -> dados = Mono.fromCallable(() ->
                                        databaseService.queryGenerica("SELECT * FROM DA_V_DA_CI_FATURA"))
                                .subscribeOn(Schedulers.boundedElastic());

                        case "pedido" -> {
                            if ("cliente".equalsIgnoreCase(tipo) && filtro != null) {
                                dados = Mono.fromCallable(() ->
                                        databaseService.queryGenerica(
                                                "SELECT * FROM PEDIDO_PAULO p " +
                                                        "JOIN CLIENTE_PAULO c ON p.ID_CLIENTE = c.ID_CLIENTE " +
                                                        "WHERE c.NOME = :nome",
                                                Map.of("nome", filtro)
                                        )).subscribeOn(Schedulers.boundedElastic());

                            } else if ("produto".equalsIgnoreCase(tipo) && filtro != null) {
                                dados = Mono.fromCallable(() ->
                                        databaseService.queryGenerica(
                                                "SELECT * FROM PEDIDO_PAULO p " +
                                                        "JOIN PRODUTO_PAULO pr ON p.ID_PRODUTO = pr.ID_PRODUTO " +
                                                        "WHERE pr.NOME = :nome",
                                                Map.of("nome", filtro)
                                        )).subscribeOn(Schedulers.boundedElastic());

                            } else {
                                dados = Mono.fromCallable(() ->
                                                databaseService.queryGenerica("SELECT * FROM PEDIDO_PAULO"))
                                        .subscribeOn(Schedulers.boundedElastic());
                            }
                        }

                        default -> dados = Mono.just(List.of(
                                Map.of("erro", "Tabela não reconhecida pelo GPT: " + tabela)
                        ));
                    }

                    // aqui o GPT recebe os dados crus e devolve resposta formatada
                    return dados.flatMap(resultado ->
                            chatGptService.askGptFormatAnswer(question, resultado));
                });
    }
}