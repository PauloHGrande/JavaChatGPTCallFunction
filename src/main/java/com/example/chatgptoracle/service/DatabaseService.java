package com.example.chatgptoracle.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatabaseService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Executa SQL genérico e retorna lista de maps
     */
    public List<Map<String, Object>> queryGenerica(String sql, Map<String, Object> params) {
        Query q = em.createNativeQuery(sql, Tuple.class);

        if (params != null) {
            params.forEach(q::setParameter);
        }

        List<Tuple> tuples = q.getResultList();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Tuple tuple : tuples) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 0; i < tuple.getElements().size(); i++) {
                String alias = tuple.getElements().get(i).getAlias();
                if (alias == null) {
                    alias = "col_" + i; // fallback se não tiver alias
                }
                row.put(alias, tuple.get(i));
            }
            result.add(row);
        }

        return result;
    }

    /**
     * Versão sem parâmetros
     */
    public List<Map<String, Object>> queryGenerica(String sql) {
        return queryGenerica(sql, Collections.emptyMap());
    }
}