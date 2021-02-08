package com.project.demo.service;

import com.project.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;
/*
    @author
    Esra KOTAN
    7 Feb 2021

 */
@Service
public class CustomRedisService {

    @Autowired
    Jedis jedis;

  private String patern = "product";

    public Product saveRedis(Product product) {
        if(product == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("productId", product.getId().toString());
        map.put("productName", product.getName());
        map.put("productPrice", product.getPrice().toString());

        Boolean isSave = false;

        try {
            //save to redis
            jedis.hmset(patern + product.getId().toString(), map);
            isSave= true;

        } catch (JedisException e) {
            isSave= false;
            System.out.println("JEDISEXCEPTION ALINDI " + e.getStackTrace());

        } finally {
            System.out.println("FINALLY BLOĞUNA GİRDİİİ");
        }
        if(isSave) {
            return product;
        }else {
            return null;
        }
    }
    public Product getToRedis(String productId) {
        if(!productId.startsWith(patern))
            productId=patern+productId;
        Map<String, String> response = jedis.hgetAll(productId);
        if(!response.containsKey("productId")) {
            return null;
        }
        Product product = new Product();
        product.setId(Long.valueOf(response.get("productId")));
        product.setName(response.get("productName"));
        product.setPrice(Long.valueOf(response.get("productPrice")));
        return product;
    }

    public List<Product> getAll(){
         List<Product> productList=new ArrayList<>();
      Set<String> keys=  jedis.keys(patern + "*");
      if(!CollectionUtils.isEmpty(keys)){
          for(String key:keys){
              Product product=getToRedis(key);
              if(product!=null)
                  productList.add(product);
          }
      }
      return productList;
    }

}
