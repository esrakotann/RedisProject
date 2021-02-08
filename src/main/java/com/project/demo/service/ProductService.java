package com.project.demo.service;
import com.project.demo.entity.Product;
import com.project.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
/*
    @author
    Esra KOTAN
    3 Feb 2021

 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    CustomRedisService redisService;


    public Product getProduct(Long id) {
        Product entity = redisService.getToRedis(id.toString());
        if(entity != null) {
            return entity;
        }else {
            entity = repository.findById(id).orElse(null);
            redisService.saveRedis(entity);
           return  entity;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> productList = redisService.getAll();
        if(CollectionUtils.isEmpty(productList)) {
            return repository.findAll();
        }else
            return productList;
    }

    public Product save(Product product) {
         Product saveProduct = repository.save(product);
         redisService.saveRedis(saveProduct);
         return saveProduct;
    }
}