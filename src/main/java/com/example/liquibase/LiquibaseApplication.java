package com.example.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LiquibaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readRunner(JdbcTemplate template) {
        return event -> template //
                .query("select * from person", (rs, rowNum) -> new Person(rs.getInt("id"), rs.getString("first_name")))//
                .forEach(System.out::println);
    }
}

record Person(Integer id, String name) {
}