package com.bcopstein.ex4_lancheriaddd.Dominio.Repositorios;

import com.bcopstein.ex4_lancheriaddd.Dominio.Entidades.PoliticaDesconto;
import java.util.List;
import java.util.Optional;

public interface PoliticaDescontoRepository {
    List<PoliticaDesconto> findAll();

    Optional<PoliticaDesconto> findByCodigo(String codigo);

    void salvar(PoliticaDesconto politica);
}
