package com.kkb.project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shige
 */
@SpringBootApplication(scanBasePackages = {"com.kkb.project"})
public class ProjectGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectGatewayApplication.class, args);
    }

}
