package com.kkb.project.portal.service;

import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectPortalServiceTest {
    @Autowired
    private ProjectPortalService projectPortalService;

    @Test
    public void getPortalProjectList() {
        List<Project> projectList = projectPortalService.getPortalProjectList(10, 0, -1, -1);
        projectList.forEach(System.out::println);
    }

    @Test
    public void getClientListById() {
//        Map<Long, Client> clientMap = projectPortalService.getClientListById(new long[]{1, 2});
//        System.out.println(clientMap);

    }


    @Test
    public void testIncrementSignupParticipantNum() {
        projectPortalService.incrementSignupParticipantNum(1);
    }

    @Test
    public void testIncrementSignupLeaderNum() {
        projectPortalService.incrementSignupLeaderNum(1);
    }

    @Test
    public void testDeleteProject() {
        projectPortalService.deleteProjectById(4);
    }
}