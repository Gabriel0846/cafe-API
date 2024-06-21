package com.gabriel.cafe.wrapper;

import lombok.Data;

@Data
public class ProductWrapper {

    Integer id;
    String nome;
    String descricao;
    Integer preco;
    String status;
    Integer categoryId;
    String categoryNome;

    public ProductWrapper() {
        
    }

    public ProductWrapper(Integer id, String nome, String descricao, Integer preco, String status, Integer categoryId, String categoryNome){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryNome = categoryNome;
    }
}
