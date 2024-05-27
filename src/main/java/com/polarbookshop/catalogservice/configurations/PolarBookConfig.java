package com.polarbookshop.catalogservice.configurations;

import com.polarbookshop.catalogservice.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class PolarBookConfig {

    @Bean
    public Map<String, Book> dataSource() {
        return new ConcurrentHashMap<>();
    }
}
