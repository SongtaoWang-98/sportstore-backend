package com.stewart.sports_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SportsStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsStoreApplication.class, args);
    }

}
