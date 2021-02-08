package com.project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

@Configuration
@ComponentScan("com.project.demo.config")
public class JedisConfiguration  {
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    @Bean
    public Jedis redisClient() {
        JedisPool pool  = new JedisPool(redisHost, redisPort);
        return pool.getResource();
    }
}

