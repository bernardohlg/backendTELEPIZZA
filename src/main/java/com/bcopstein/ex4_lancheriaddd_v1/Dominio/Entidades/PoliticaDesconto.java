package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades;

public class PoliticaDesconto {
    private String codigo;
    private String descricao;
    private double percentual;
    private boolean corrente;

    public PoliticaDesconto(String codigo, String descricao, double percentual, boolean corrente) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.percentual = percentual;
        this.corrente = corrente;
    }

    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public double getPercentual() { return percentual; }
    public boolean isCorrente() { return corrente; }
    public void setCorrente(boolean corrente) { this.corrente = corrente; }
}
