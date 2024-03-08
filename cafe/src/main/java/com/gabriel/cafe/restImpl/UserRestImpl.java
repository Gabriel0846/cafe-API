package com.gabriel.cafe.restImpl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.cafe.rest.UserRest;

@RestController
public class UserRestImpl implements UserRest {

    @Override
    public ResponseEntity<String> sigUp(Map<String, String> requestMap) {
        return null;
    }
    
}
