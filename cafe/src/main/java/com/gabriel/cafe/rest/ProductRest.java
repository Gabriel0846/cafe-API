package com.gabriel.cafe.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/produto")
public interface ProductRest {

    @PostMapping(path = "/adicionar")
    ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap);

}
