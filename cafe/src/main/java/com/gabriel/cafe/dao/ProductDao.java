package com.gabriel.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.cafe.POJO.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}

