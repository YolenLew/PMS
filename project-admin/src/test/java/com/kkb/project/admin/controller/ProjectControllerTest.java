package com.kkb.project.admin.controller;

import com.kkb.project.admin.domain.ProjectWorkStyle;
import com.kkb.project.admin.domain.constant.ProjectStatus;
import com.kkb.project.admin.domain.dto.QueryProjectDto;
import com.kkb.project.admin.domain.vo.ProjectVo;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;

    @Test
    public void getAllProjects() throws Exception {
        QueryProjectDto q = new QueryProjectDto(-1L, ProjectStatus.PROCESSING.value(), -1, 1L, -1L);

        CommonResult<CommonPage<ProjectVo>> allProjects = projectController.getAllProjects(q);
        allProjects.getData().getList().forEach(System.out::println);
    }

    @Test
    public void addNewProject() {

    }

    @Test
    public void updateProject() {
    }

    @Test
    public void getAllWorkStyles() {
        CommonResult<Map<Long, ProjectWorkStyle>> res = projectController.getAllWorkStyles();
    }

    @Test
    public void getAllWorkTypes() {
        projectController.getAllWorkTypes();
    }

    @Test
    public void deleteProject() {
    }
}