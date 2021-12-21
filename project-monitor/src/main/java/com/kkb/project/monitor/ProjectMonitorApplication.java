package com.kkb.project.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author River
 */
@EnableAdminServer
@SpringBootApplication
public class ProjectMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMonitorApplication.class, args);
    }

}
