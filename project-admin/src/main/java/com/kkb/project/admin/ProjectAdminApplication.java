package com.kkb.project.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @ClassName ProjectAdminApplication
 * @Author River
 * @Date 2021/4/21 20:08
 * @Description admin启动类
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.kkb.project")
//@EnableDiscoveryClient
public class ProjectAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAdminApplication.class, args);
    }

}
