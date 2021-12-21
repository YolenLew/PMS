package com.kkb.project.portal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParticipantServiceTest {

    @Autowired
    private ParticipantService participantService;


    @Test
    public void testParticipant(){
        participantService.participantSignUp(1, 9);
    }

}
