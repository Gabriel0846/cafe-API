package com.gabriel.cafe.serviceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gabriel.cafe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return null;
    }
    
}
