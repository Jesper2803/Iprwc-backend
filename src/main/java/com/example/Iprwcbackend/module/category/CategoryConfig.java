package com.example.Iprwcbackend.module.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner commandLineRunnerCategory(CategoryRepository repository){
        return args -> {

//            Category mariam = new Category(
//                    "ssfd"
//            );
//            repository.saveAll(
//                    List.of(mariam)
//            );
        };

    }
}
