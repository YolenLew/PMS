package com.kkb.project.admin;


import com.kkb.project.admin.domain.vo.SuccessCaseVo;
import com.kkb.project.admin.service.SuccessCaseService;
import com.kkb.project.common.api.CommonPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ProjectAdminApplicationTests {

    @Autowired
    protected SuccessCaseService successCaseService;




    @Test
    void findProjectIdById() {
        CommonPage<SuccessCaseVo> successCase = successCaseService.findSuccessCase(1, 1);
        System.out.println(successCase);
    }

}
