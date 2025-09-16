package com.example.chatgptoracle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "PEDIDO_PAULO")
public class Pedido {

    @Id
    @Column(name = "ID_PEDIDO")
    private Long idPedido;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "ID_PRODUTO")
    private Long idProduto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    // getters e setters
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
}