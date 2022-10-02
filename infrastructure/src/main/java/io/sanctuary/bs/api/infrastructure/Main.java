package io.sanctuary.bs.api.infrastructure;

import io.sanctuary.bs.api.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}