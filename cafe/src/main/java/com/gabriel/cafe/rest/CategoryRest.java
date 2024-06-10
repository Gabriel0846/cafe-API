package com.gabriel.cafe.rest;


import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/categoria")
public interface CategoryRest {
    
    @PostMapping(path = "/adicionar")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true)Map<String, String> requestMap);

}
