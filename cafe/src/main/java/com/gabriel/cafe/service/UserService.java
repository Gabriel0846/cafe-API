package com.gabriel.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    
    ResponseEntity<String> cadastrar(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);
}
