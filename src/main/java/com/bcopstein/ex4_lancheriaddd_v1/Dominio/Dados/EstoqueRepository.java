package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados;

import java.util.Map;

public interface EstoqueRepository {

    //chave = id do ingrediente, valor = quantidade disponível
    Map<Long, Integer> recuperaEstoque();
}
