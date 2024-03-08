package com.gabriel.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    
    ResponseEntity<String> login(Map<String, String> requestMap);
}
