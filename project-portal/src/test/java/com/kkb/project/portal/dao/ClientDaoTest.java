package com.kkb.project.portal.dao;

import com.kkb.project.portal.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDaoTest {
    @Autowired
    private ClientDao clientDao;

    @Test
    public void testCreateOneClient() {
        Client c = new Client("xu_yu_cong", "kkb-0", "https://localhost:3309/test-uri");
        int r = clientDao.insert(c);
        System.out.println(r);
    }

}