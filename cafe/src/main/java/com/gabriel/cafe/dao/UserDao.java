package com.gabriel.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gabriel.cafe.POJO.User;
import com.gabriel.cafe.wrapper.UserWrapper;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);
    
    List<UserWrapper> getAllUser();
}