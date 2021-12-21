package com.kkb.project.admin.service.impl;

import com.kkb.project.admin.domain.dto.ProjectAdminDto;
import com.kkb.project.admin.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author: YNB
 * @Description:
 * @Date Created in 2021-05-01 11:09
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void addNewProject() {
        ProjectAdminDto projectAdminDto = new ProjectAdminDto("测试1",1L,new Date(),2L,"Admin测试","Java",
                "alibaba",23,new Date(),"ynb");
        projectService.addNewProject(projectAdminDto);
    }
}