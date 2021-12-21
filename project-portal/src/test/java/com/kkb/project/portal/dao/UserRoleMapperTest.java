package com.kkb.project.portal.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRoleMapperTest {

    @Test
    public void test3(){
        PathMatcher pathMatcher = new AntPathMatcher();
        String path = "/portal/internal/recommendation/get/1";
        String pattern = "/internal/recommendation/get/*";
        System.out.println(pathMatcher.match(pattern, path));
    }
}