package com.cristallium.brendon.imobiliario;

public class Imovel {

    private Integer valor;
    private String endereco;
    private String informacoes;
    private String proprietario;

    public Imovel(String endereco, String proprietario, String informacoes, Integer valor) {
        setValues(endereco, proprietario, informacoes, valor);
    }

    public void setValues(String endereco, String proprietario, String informacoes, Integer valor) {
        this.valor = valor;
        this.endereco = endereco;
        this.informacoes = informacoes;
        this.proprietario = proprietario;
    }

    public Integer getValor() {
        return valor;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public String getProprietario() {
        return proprietario;
    }

}
