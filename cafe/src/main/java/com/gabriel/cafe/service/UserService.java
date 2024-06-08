package com.gabriel.cafe.service;

import org.springframework.http.ResponseEntity;

import com.gabriel.cafe.wrapper.UserWrapper;

import java.util.List;
import java.util.Map;

public interface UserService {
    
    ResponseEntity<String> cadastrar(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checarToken();

    ResponseEntity<String> mudarSenha(Map<String, String> requestMap);
}
