package com.example.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RecipeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Recipe("ONE POT CREAMY CAJUN CHICKEN PASTA", "https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/")));
            log.info("Preloading " + repository.save(new Recipe("EASY LEMON PEPPER CHICKEN", "https://www.budgetbytes.com/easy-lemon-pepper-chicken/")));
        };
    }
}
