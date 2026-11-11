package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Apresentacao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Apresentacao.Presenters.CabecalhoCardapioPresenter;
import com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Apresentacao.Presenters.CardapioPresenter;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.CarregarCardapioUC;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.DefinirCardapioCorrenteUC;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.RecuperaListaCardapiosUC;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.RecuperarCardapioUC;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.Responses.CardapioResponse;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.CabecalhoCardapio;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Produto;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    private RecuperarCardapioUC recuperaCardapioUC;
    private RecuperaListaCardapiosUC recuperaListaCardapioUC;
    private DefinirCardapioCorrenteUC definirCardapioCorrenteUC;
    private CarregarCardapioUC carregarCardapioUC;

    public CardapioController(RecuperarCardapioUC recuperaCardapioUC,
                              RecuperaListaCardapiosUC recuperaListaCardapioUC,
                              DefinirCardapioCorrenteUC definirCardapioCorrenteUC,
                              CarregarCardapioUC carregarCardapioUC) {
        this.recuperaCardapioUC = recuperaCardapioUC;
        this.recuperaListaCardapioUC = recuperaListaCardapioUC;
        this.definirCardapioCorrenteUC = definirCardapioCorrenteUC;
        this.carregarCardapioUC = carregarCardapioUC;
    }

    @GetMapping("/{id}")
    @CrossOrigin("*")
    public CardapioPresenter recuperaCardapio(@PathVariable(value="id")long id){
        CardapioResponse cardapioResponse = recuperaCardapioUC.run(id);
        Set<Long> conjIdSugestoes = new HashSet<>(cardapioResponse.getSugestoesDoChef().stream()
            .map(produto->produto.getId())
            .toList());
        CardapioPresenter cardapioPresenter = new CardapioPresenter(cardapioResponse.getCardapio().getCabecalhoCardapio().titulo());
        for(Produto produto:cardapioResponse.getCardapio().getProdutos()){
            boolean sugestao = conjIdSugestoes.contains(produto.getId());
            cardapioPresenter.insereItem(produto.getId(), produto.getDescricao(), produto.getPreco(), sugestao);
        }
        return cardapioPresenter;
    }

    @GetMapping("/lista")
    @CrossOrigin("*")
    public List<CabecalhoCardapioPresenter> recuperaListaCardapios(){
         List<CabecalhoCardapioPresenter> lstCardapios = 
            recuperaListaCardapioUC.run().cabecalhos().stream()
            .map(cabCar -> new CabecalhoCardapioPresenter(cabCar.id(),cabCar.titulo()))
            .toList();
         return lstCardapios;
    }

    @PutMapping("/corrente/{id}")
    @CrossOrigin("*")
    public ResponseEntity<CabecalhoCardapioPresenter> definirCardapioCorrente(@PathVariable(value="id")long id){
        CabecalhoCardapio cabecalho = definirCardapioCorrenteUC.run(id);
        if (cabecalho == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CabecalhoCardapioPresenter(cabecalho.id(), cabecalho.titulo()));
    }

    @GetMapping("/corrente")
    @CrossOrigin ("*")
    public ResponseEntity<CardapioPresenter> carregarCardapio(){
        CardapioResponse cardapioResponse = carregarCardapioUC.run();
        if(cardapioResponse == null) {
            return ResponseEntity.notFound().build(); //nenhum cardapio 404
        }
        Set<Long> conjIdSugestoes = new HashSet<> (cardapioResponse.getSugestoesDoChef().stream().map(produto -> produto.getId()).toList());
        CardapioPresenter cardapioPresenter = new CardapioPresenter(cardapioResponse.getCardapio().getCabecalhoCardapio().titulo());
        for (Produto produto : cardapioResponse.getCardapio().getProdutos()) {
            boolean sugestao = conjIdSugestoes.contains(produto.getId());
            cardapioPresenter.insereItem(produto.getId(), produto.getDescricao(), produto.getPreco(), sugestao);
        }
        return ResponseEntity.ok(cardapioPresenter);   // 200: cardápio corrente
    }
    
}
