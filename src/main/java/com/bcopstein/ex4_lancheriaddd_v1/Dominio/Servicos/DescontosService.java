package com.bcopstein.ex4_lancheriaddd.Dominio.Servicos;

import com.bcopstein.ex4_lancheriaddd.Dominio.Entidades.PoliticaDesconto;
import com.bcopstein.ex4_lancheriaddd.Dominio.Repositorios.PoliticaDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DescontosService {
    @Autowired
    private PoliticaDescontoRepository repository;

    public List<PoliticaDesconto> run() {
        return repository.findAll();
    }
}
