package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.PoliticaDesconto;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.PoliticaDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescontosService {

    @Autowired
    private PoliticaDescontoRepository repository;

    // UC3: Listar as políticas de desconto disponíveis
    public List<PoliticaDesconto> listarPoliticas() {
        return repository.findAll();
    }

    // UC4: Definir a política de desconto corrente
    public boolean definirPoliticaCorrente(String codigo) {
        var optPolitica = repository.findByCodigo(codigo);
        if (optPolitica.isEmpty()) {
            return false;
        }

        List<PoliticaDesconto> todas = repository.findAll();
        for (PoliticaDesconto p : todas) {
            boolean ehCorrente = p.getCodigo().equals(codigo);
            PoliticaDesconto atualizada = new PoliticaDesconto(p.getCodigo(), p.getDescricao(), p.getPercentual(),
                    ehCorrente);
            repository.salvar(atualizada);
        }
        return true;
    }
}
