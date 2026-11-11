package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.PoliticaDesconto;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.PoliticaDescontoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PoliticaDescontoRepositoryJDBC implements PoliticaDescontoRepository {
    private static final List<PoliticaDesconto> politicas = new ArrayList<>(List.of(
            new PoliticaDesconto("DESC01", "Desconto Padrão (7% para >3 pedidos em 20 dias)", 0.07, true),
            new PoliticaDesconto("DESC02", "Sem Desconto", 0.0, false)));

    @Override
    public List<PoliticaDesconto> findAll() {
        return new ArrayList<>(politicas); // Retorna uma cópia para segurança
    }

    @Override
    public Optional<PoliticaDesconto> findByCodigo(String codigo) {
        return politicas.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
    }

    @Override
    public void salvar(PoliticaDesconto politica) {
        // Remove a versão antiga pelo código e adiciona a atualizada sem quebrar o
        // iterador
        politicas.removeIf(p -> p.getCodigo().equals(politica.getCodigo()));
        politicas.add(politica);
    }
}
