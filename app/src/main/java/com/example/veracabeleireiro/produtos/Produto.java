package com.example.veracabeleireiro.produtos;

import java.math.BigDecimal;

public class Produto {
    private String nome;
    private BigDecimal preco;
    private String imagemUrl;

    public Produto(String nome, BigDecimal preco, String imagemUrl) {
        this.nome = nome;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
}
