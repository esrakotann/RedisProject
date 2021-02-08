package com.project.demo.rest;

import com.project.demo.entity.Product;
import com.project.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

/*
    @author
    Esra KOTAN
    3 Feb 2021

 */
@RestController
@RequestMapping("/product")
public class ProductREST {

    @Autowired private ProductService productService;

    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam("id") Long id) {
        Product product = productService.getProduct(id);
        return product;
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @PostMapping("/postProduct")
    public Product postProduct(@RequestBody Product product) {
        Product entity = null;
         entity = productService.save(product);
     return entity;
    }
}
