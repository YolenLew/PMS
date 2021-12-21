package com.kkb.project.portal.service.impl;

import com.kkb.project.portal.domain.WorkImage;
import com.kkb.project.portal.service.WorkImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyunpeng
 * @version V1.0.1
 * @description
 * @date 2021-04-27 21:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkImageServiceImplTest {
    @Resource
    private WorkImageService imageService;

    @Test
    public void listImagesByUserId() {
        Map<Long, List<WorkImage>> listMap = imageService.listImagesByUserId(0L);
        Set<Long> userWorkIds = listMap.keySet();
        for (Long wid : userWorkIds){
            System.out.println("-------------------");
            System.out.println(wid);
            for (WorkImage workImage : listMap.get(wid)){
                System.out.println(workImage);
            }
        }
    }
}