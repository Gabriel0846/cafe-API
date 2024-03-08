package com.gabriel.cafe.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/usuario")
public interface UserRest {
    
    @PostMapping(path = "/loguin")
    public ResponseEntity<String> sigUp(@RequestBody(required = true) Map<String, String> requestMap);
}
