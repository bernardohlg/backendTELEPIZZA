package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.EstoqueRepository;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.IngredientesRepository;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Ingrediente;

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
        return estoque;

        String sql = "SELECT id, descri"

    }
    
}
