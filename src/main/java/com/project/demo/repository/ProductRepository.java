package com.project.demo.repository;

import com.project.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
    @author
    Esra KOTAN
    3 Feb 2021

 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
