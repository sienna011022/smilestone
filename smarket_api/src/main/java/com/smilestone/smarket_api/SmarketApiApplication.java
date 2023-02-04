package com.smilestone.smarket_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmarketApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarketApiApplication.class, args);
    }

}
