package com.kkb.project.portal.service;

import com.kkb.project.portal.dao.LeaderSignupDao;
import com.kkb.project.portal.domain.LeaderSignup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: YNB
 * @Description:
 * @Date Created in 2021-04-14 21:26
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderServiceTest  {
    @Autowired
    LeaderService leaderService;

    @Autowired
    LeaderSignupDao leaderSignupDao;

    /**
     * 测试数据是否插入成功
     */
    @Test
    public void leaderSignup() {
//        boolean b = leaderService.leaderSignup(56, 12);
//        System.out.println(b);
    }

    /**
     * 测试能否将数据库的数据查询出来
     */
    @Test
    public  void  signupList(){
        //LeaderSignup l = leaderSignupDao.selectById(null);
        List<LeaderSignup> l = leaderSignupDao.selectList(null);
        System.out.println(l);
    }
}