package com.gabriel.cafe.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String status;

    public UserWrapper(Integer id, String nome, String email, String telefone, String status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
    }
}
