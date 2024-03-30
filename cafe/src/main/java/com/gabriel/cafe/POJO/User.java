package com.gabriel.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Data;

@NamedNativeQuery(name = "User.findByEmailId", query = "select u.email from usuario u where u.email = :email")

@NamedNativeQuery(name = "User.getAllUser", query = "select u.id, u.nome, u.email, u.telefone, u.status from usuario u where u.perfil = 'user'")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "usuario")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "perfil")
    private String perfil;
    
}

