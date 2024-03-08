package com.gabriel.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.cafe.POJO.User;

public interface UserDao extends JpaRepository<User, Integer> {
    
}
