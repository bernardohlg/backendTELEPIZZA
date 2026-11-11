package com.bcopstein.ex4_lancheriaddd_v1.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.Responses.CardapioResponse;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Cardapio;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Produto;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.CardapioService;

//uc5 o cliente pede o cardapio e corrente para montar o pedido
@Component // para avisar o string para eke entregar para quem precisa 
public class CarregarCardapioUC {
    private CardapioService cardapioService;

    @Autowired
    public RecuperarCardapioUC(CardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CardapioResponse run(long idCardapio){
        Cardapio cardapio = cardapioService.recuperaCardapio(idCardapio);
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();
        return new CardapioResponse(cardapio,sugestoes);
    }
    
}



