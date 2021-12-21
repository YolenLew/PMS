package com.kkb.project.portal.service;

import com.kkb.project.portal.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService service;

    @Test
    public void insertOneClient() {
        Client c = new Client("xu_yu_cong", "kkb", "https://localhost:3309/test-uri");
//        c.setId(0L);
        boolean r = service.insertOneClient(c);
        System.out.println(r);
    }

    @Test
    public void testGetClientById() {
        long id = 2L;
        Client client = service.getClientById(id);
        System.out.println(client);
    }

    @Test
    public void testList() {
        service.listByIds(new ArrayList<>());
    }
}