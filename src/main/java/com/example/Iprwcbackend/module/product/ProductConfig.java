package com.example.Iprwcbackend.module.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepository repository){
        return args -> {

//            Product mariam = new Product(
//                    "ssfd", "maria", 2, 2, "ds"
//            );
//            repository.saveAll(
//                    List.of(mariam)
//            );
        };

    }
}
