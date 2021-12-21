package com.kkb.project.portal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author 张观林
 * @Date 2021/4/21 1:02
 */
@SpringBootTest
class WorkImageServiceTest {
    @Autowired
    private WorkImageService imageService;
    @Autowired
    private WorkDescriptionService descriptionService;
    @Autowired
    private UserWorkService workService;
    @Test
    void findImagesByWorksId() {
        System.out.println(imageService.findImagesByWorksId(1L,1L));
    }
    @Test
    void findWorkDescriptionById(){
        System.out.println(descriptionService.findWorkDescriptionById(1L));
    }
    @Test
    void findWorkCreateTimeById(){
        System.out.println(workService.findWorkCreateTimeById(1L));
    }
    @Test
    void findUserWorkById(){
        workService.findUserWorkById(1L);
    }
}