package com.kkb.project.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 前台门户启动类
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.kkb.project")
public class ProjectPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPortalApplication.class, args);
    }

}
