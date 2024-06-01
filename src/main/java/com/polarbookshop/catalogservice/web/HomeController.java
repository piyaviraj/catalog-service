package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.configurations.PolarProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final PolarProperties properties;

    @GetMapping("/")
    public String home() {
        return properties.getGreeting();
    }
}
