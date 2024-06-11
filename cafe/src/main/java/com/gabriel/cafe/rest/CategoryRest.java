package com.gabriel.cafe.rest;


import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gabriel.cafe.POJO.Category;

@RequestMapping(path = "/categoria")
public interface CategoryRest {
    
    @PostMapping(path = "/adicionar")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true)Map<String, String> requestMap);

    @GetMapping(path = "/buscar")
    ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String filterValue);

    @PostMapping(path = "/atualizar")
    ResponseEntity<String> updateCategoria(@RequestBody(required = true)Map<String, String> requestMap);
}
