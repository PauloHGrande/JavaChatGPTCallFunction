package com.example.chatgptoracle.service;

import com.example.chatgptoracle.model.Cliente;
import com.example.chatgptoracle.model.Faturas;
import com.example.chatgptoracle.model.Pedido;
import com.example.chatgptoracle.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    @PersistenceContext
    private EntityManager em;

    // Consulta todos os produtos
    public List<Produto> getProdutos() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();
    }

    // Consulta todos os clientes
    public List<Cliente> getClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    // Consulta todos os pedidos
    public List<Pedido> getPedidos() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class)
                .getResultList();
    }

    // Busca cliente por nome (retorna null se não encontrar)
    public Cliente findClienteByNome(String nome) {
        List<Cliente> result = em.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Busca produto por nome (retorna null se não encontrar)
    public Produto findProdutoByNome(String nome) {
        List<Produto> result = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Consulta pedidos por cliente (usando ID direto)
    public List<Pedido> getPedidosByCliente(Long clienteId) {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.idCliente = :cid", Pedido.class)
                .setParameter("cid", clienteId)
                .getResultList();
    }

    // Consulta pedidos por produto (usando ID direto)
    public List<Pedido> getPedidosByProduto(Long produtoId) {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.idProduto = :pid", Pedido.class)
                .setParameter("pid", produtoId)
                .getResultList();
    }

    public Integer getQuantidadeProdutosByClienteNome(String nomeCliente) {
        Cliente cliente = findClienteByNome(nomeCliente);
        if (cliente == null) {
            return null; // ou lançar exceção / retornar 0
        }

        Long total = em.createQuery(
                        "SELECT SUM(p.quantidade) FROM Pedido p WHERE p.idCliente = :cid", Long.class)
                .setParameter("cid", cliente.getIdCliente())
                .getSingleResult();

        return total != null ? total.intValue() : 0;
    }

    // Consulta todos os pedidos
    public List<Faturas> getFaturas() {
        return em.createQuery("SELECT p FROM Faturas p", Faturas.class)
                .getResultList();
    }

}