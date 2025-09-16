package com.example.chatgptoracle.controller;

import com.example.chatgptoracle.model.Cliente;
import com.example.chatgptoracle.model.Produto;
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

import java.util.List;

@RestController
public class QueryController {

    private final ChatGptService chatGptService;
    private final DatabaseService databaseService;

    public QueryController(ChatGptService chatGptService, DatabaseService databaseService) {
        this.chatGptService = chatGptService;
        this.databaseService = databaseService;
    }

    @Operation(summary = "Consulta tabelas usando ChatGPT Function-Calling",
            description = "O ChatGPT decide qual tabela consultar (produto, cliente, pedido) e retorna os dados")
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    @GetMapping("/query")
    public Mono<Object> query(
            @Parameter(description = "Pergunta que o ChatGPT deve interpretar")
            @RequestParam String question) {

//        return chatGptService.askGptFunctionCalling(question)
//                .flatMap(functionCall -> {
//                    String tabela = functionCall.tabela.toLowerCase();
//                    String filtro = functionCall.filtro;
//                    String tipo = functionCall.tipo;
//
//                    return switch (tabela) {
//                        case "produto" -> Mono.fromCallable(() -> databaseService.getProdutos())
//                                .subscribeOn(Schedulers.boundedElastic());
//
//                        case "cliente" -> Mono.fromCallable(() -> databaseService.getClientes())
//                                .subscribeOn(Schedulers.boundedElastic());
//
//                        case "pedido" -> {
//                            if (filtro != null && !filtro.isBlank() && tipo != null) {
//                                yield Mono.fromCallable(() -> {
////                                    if ("cliente".equalsIgnoreCase(tipo)) {
////                                        Cliente cliente = databaseService.findClienteByNome(filtro);
////                                        return cliente != null ? databaseService.getPedidosByCliente(cliente.getIdCliente())
////                                                : List.of("Cliente não encontrado: " + filtro);
//                                    if ("cliente".equalsIgnoreCase(tipo)) {
//                                        Cliente cliente = databaseService.findClienteByNome(filtro);
//                                        if (cliente == null) {
//                                            return Mono.just(List.of("Cliente não encontrado: " + filtro));
//                                        }
//
//                                        // Aqui você pode escolher: se a pergunta contiver "quantidade", retorna soma
//                                        if (question.toLowerCase().contains("quantidade")) {
//                                            Integer total = databaseService.getQuantidadeProdutosByClienteNome(filtro);
//                                            return Mono.just("Cliente " + filtro + " comprou " + total + " produtos no total.");
//                                        } else {
//                                            // Caso contrário, retorna a lista de pedidos
//                                            return Mono.fromCallable(() -> databaseService.getPedidosByCliente(cliente.getIdCliente()))
//                                                    .subscribeOn(Schedulers.boundedElastic());
//                                        }
//                                    } else if ("produto".equalsIgnoreCase(tipo)) {
//                                        Produto produto = databaseService.findProdutoByNome(filtro);
//                                        return produto != null ? databaseService.getPedidosByProduto(produto.getIdProduto())
//                                                : List.of("Produto não encontrado: " + filtro);
//                                    } else {
//                                        return List.of("Tipo inválido: " + tipo);
//                                    }
//                                }).subscribeOn(Schedulers.boundedElastic());
//                            } else {
//                                yield Mono.fromCallable(() -> databaseService.getPedidos())
//                                        .subscribeOn(Schedulers.boundedElastic());
//                            }
//                        }
//
//                        default -> Mono.just("Tabela não reconhecida pelo GPT");
//                    };
//                });
        return chatGptService.askGptFunctionCalling(question)
                .flatMap(functionCall -> {
                    String tabela = functionCall.tabela.toLowerCase();
                    String filtro = functionCall.filtro;
                    String tipo = functionCall.tipo;

                    Mono<? extends List<?>> dados;

                    switch (tabela) {
                        case "produto" ->
                                dados = Mono.fromCallable(databaseService::getProdutos)
                                        .subscribeOn(Schedulers.boundedElastic());

                        case "cliente" ->
                                dados = Mono.fromCallable(databaseService::getClientes)
                                        .subscribeOn(Schedulers.boundedElastic());

                        case "faturas" ->
                                dados = Mono.fromCallable(databaseService::getFaturas)
                                        .subscribeOn(Schedulers.boundedElastic());

                        case "pedido" -> {
                            if ("cliente".equalsIgnoreCase(tipo) && filtro != null) {
                                dados = Mono.fromCallable(() -> {
                                    Cliente cliente = databaseService.findClienteByNome(filtro);
                                    return cliente != null
                                            ? databaseService.getPedidosByCliente(cliente.getIdCliente())
                                            : List.of("Cliente não encontrado: " + filtro);
                                }).subscribeOn(Schedulers.boundedElastic());
                            } else if ("produto".equalsIgnoreCase(tipo) && filtro != null) {
                                dados = Mono.fromCallable(() -> {
                                    Produto produto = databaseService.findProdutoByNome(filtro);
                                    return produto != null
                                            ? databaseService.getPedidosByProduto(produto.getIdProduto())
                                            : List.of("Produto não encontrado: " + filtro);
                                }).subscribeOn(Schedulers.boundedElastic());
                            } else {
                                dados = Mono.fromCallable(databaseService::getPedidos)
                                        .subscribeOn(Schedulers.boundedElastic());
                            }
                        }

                        default -> dados = Mono.just(List.of("Tabela não reconhecida pelo GPT"));
                    }

                    // aqui o GPT recebe os dados crus e devolve resposta pronta
                    return dados.flatMap(resultado -> chatGptService.askGptFormatAnswer(question, resultado));
                });
    }

}