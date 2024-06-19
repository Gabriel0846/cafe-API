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
}
