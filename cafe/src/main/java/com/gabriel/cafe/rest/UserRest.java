package com.gabriel.cafe.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gabriel.cafe.wrapper.UserWrapper;

@RequestMapping(path = "/usuario")
public interface UserRest {
    
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);
    
    @GetMapping(path = "/buscar")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "atualizar")
    public ResponseEntity<String> atualizar(@RequestBody(required = true) Map<String, String> requestMap);
}
