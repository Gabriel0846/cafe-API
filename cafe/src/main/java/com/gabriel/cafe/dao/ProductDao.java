package com.gabriel.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.cafe.POJO.Product;
import com.gabriel.cafe.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<ProductWrapper> getAllProduct();

}

