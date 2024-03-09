package com.gabriel.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.cafe.POJO.User;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM usuario u WHERE u.email = :email", nativeQuery = true)
    User findByEmailId(@Param("email") String email);
    
}
