package com.example.openpayd_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableFeignClients
@EnableAuthorizationServer
@EnableResourceServer
public class OpenpaydClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenpaydClientApplication.class, args);
    }

}
