package com.example.Iprwcbackend.module.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productRunner(ProductRepository repository){
        return args -> {
            Product test = new Product("test", "test", 2, 3, "https://www.workman.nl/media/products/0304-H-Front-WorkMan-Professional-Workwear-Super-Heavy-T-Shirt-ROYALBLUE.png");
            repository.save(test);
        };
    }
}
