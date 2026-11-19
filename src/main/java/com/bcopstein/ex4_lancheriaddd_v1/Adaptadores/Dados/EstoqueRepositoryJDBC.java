package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.EstoqueRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EstoqueRepositoryJDBC implements EstoqueRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired 
    public EstoqueRepositoryJDBC (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override 
    public Map<Long,Integer> recuperaEstoque(){
        Map<Long, Integer> estoque = new HashMap<>();

        String sql = "SELECT quantidade, ingrediente_id FROM itensEstoque WHERE corrente = true"; 
        this.jdbcTemplate.query(sql, rs -> {
                                long idIngrediente = rs.getLong("idIngredientes");
                                int quantidade = rs.getInt("quantidade");
                                estoque.put(idIngrediente , quantidade);         
    });  
    this.jdbcTemplate.
        return estoque;
       
        // Map<Long, Integer> ingredientes =    

    }
    
}
