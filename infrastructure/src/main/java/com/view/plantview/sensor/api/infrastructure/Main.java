package com.view.plantview.sensor.api.infrastructure;

import com.view.plantview.sensor.api.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableEurekaClient
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}