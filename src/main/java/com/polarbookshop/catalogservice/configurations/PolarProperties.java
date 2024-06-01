package com.polarbookshop.catalogservice.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

    /**
     * A message to welcome users.
     */
    private String greeting;
}
