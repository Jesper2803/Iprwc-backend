package com.example.Iprwcbackend.module.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerProduct(UserRepository repository){
        return args -> {

            User admin = new User(
                    "admin", "admin", "admin", "adminuser@admin.com", Role.ROLE_ADMIN
            );
            repository.saveAll(
                    List.of(admin)
            );
        };
    }
}
