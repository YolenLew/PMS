package com.kkb.project.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages = {"com.kkb.project"})
@EnableResourceServer
public class ProjectAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAuthApplication.class, args);

    }
}
