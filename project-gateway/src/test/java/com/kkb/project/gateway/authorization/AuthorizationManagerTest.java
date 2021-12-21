package com.kkb.project.gateway.authorization;

import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorizationManagerTest {

    @Test
    public void test(){
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTk1MTY0MjEsInVzZXJfbmFtZSI6ImxlbW9uIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9hZG1pbiJdLCJqdGkiOiJkNzdkMTI0OS1kZDVmLTRmNTQtYWU0ZS1iMWVmODk4ZTI1NDMiLCJjbGllbnRfaWQiOiJra2IiLCJzY29wZSI6WyJhbGwiXX0.VXBSiIFw-y8ioYvwIaA9_bAj3O3mqmwvyUnKld8maXAFRMCubPf4m-oFMhWzwFVrMmtUUo6CXUuqiTvgX71DaM-65THkwCoNKgvf_CBbSfVrRjoWjEGlwUUWjjGU5NUHwNAwmhOGZ3iNMkgQh_cDs3cOe5dcHy5EbEjWQXqXsn1iL-24Sqb0orpgrQPdA_nhTuUkfWNzi8mNDAsYmX_9YKvSw-mu1gHHwrACDbo1s8GOJNoXWxzjZN58YSLEGtX950zu5oWL46r-KQy6ZFIAGoDe0i5JAccb-Xpmrtl2fJJwtpiJyOQWDbYV7qVrnW_0JioAGBX0zfbpOEcz_H8rHw";
        try {
            JWSObject parse = JWSObject.parse(token);
            System.out.println(parse.getPayload().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTk3Njk0OTAsInVzZXJfbmFtZSI6ImxlbW9uIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNTI4Y2RhNDUtNzNhZC00MmM1LWE2ZjgtODk4NjBhN2M1OGMxIiwiY2xpZW50X2lkIjoia2tiIiwic2NvcGUiOlsiYWxsIl19.SeGGEEkZP9UZIrA8f8lbQCmgBKb6qMjpiav0_sa-ORnK00rXzGeoKh9QsxwM4BMY7e3VVl06vkftbtdnFlBXf41pLQP6FlmGZUJyDZp2Q2IdJ45LnUddMCTl9wqpVT8661AzijJ2agDsr-VLP9hqwB_-xGjCGfMdvwfucENUwJM5dH-4gOnf5rHdwB068BV60-8c2Xtrx6R7IZvXSGu1P9duTovC1WarW-iKaR3XuCax00wLewzItqCukztFDI7ydb_jOGObxsftAObvx8axrPDML9iG6d211HyY3HxkrWJD5Q6wfIvwcs5ZMlgedqLhh94W7DdjD1HY866GyVA0Aw";
        String token1 = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTk3NzE1MjIsInVzZXJfbmFtZSI6ImxlbW9uIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9zdHVkZW50Il0sImp0aSI6ImVhNjI3MjgyLTIzYWUtNDA1YS04NWVlLTFlZDVmZmRkMWNmZiIsImNsaWVudF9pZCI6ImtrYiIsInNjb3BlIjpbImFsbCJdfQ.aKHcDYJe3-_X83rfXHcQ3QKZYtX87MyqpRVSM4sbpJCRKqEL3AiR9FDuqf2JdhrhpJjYJP1F22zjE2Gh5ameF3bMfXaLXy32loZ-OhE3ljweAOXnqKqxokCgV4wo2sJArpBSdS_b4FqLPLpGg_-y3MQrGaPla2NQ7Pb3L8DHVTTIlgMvIoAc0MdAI6dFfBup-CLTfage5Am687YBK-O93bldHXIioidEx7whfDmaqxbwEknu8aYI09p4M1-eZYwZaQ4Zgr-4i65pPueLwNaJBjbC9a_Cgmot_ggd3KBkh1QwxkERPyWkgiWOHxS7miAfKk_LV0hFhaIMnk2_3vSd7w";
        String token3 = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTk4NTQzMzQsInVzZXJfbmFtZSI6IjEzMzY2NjY4ODg4IiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9jdXN0b20iLCJST0xFX3RlYWNoZXIiXSwianRpIjoiYzI0ZGQ4M2MtNGEyMC00MzM5LWE0NTYtMzI5YjNiMzcxZGM3IiwiY2xpZW50X2lkIjoia2tiIiwic2NvcGUiOlsiYWxsIl19.gWlUS6NXajyvXGu7EBKQoJO_A-BqaNWU-4buvH-KIG1XeFx6Oab0OWbjDVtfRV3f8HeBiyW9wkDQPtTrmxpT-N2gBEajOFIvUmD1vqLCQU3vUZ0JOpqBsWe-6QGSuvaCDYe9A3BygInsnWRZhAb1WmLfIqKFAfH1Sv7CJKti6A3-eHOdfBnsaq0gwY96YA-XiDWwd7ziJkqSjz-SK_xpUBabZY34mqRdLRCtHScfcNKgffPBW4nPZtww7MVj684Hr7SYgL3xVNYhVvOEYfXNMiP_IQMxeNmyrLg-b6216qy94q5l-g6GX2wCG3T1XfIsCbt0rm9vh1I54X0MDjChGA";
        try {
            JWSObject parse = JWSObject.parse(token3);
            /*JSONObject jsonObject = parse.getPayload().toJSONObject();
            List<String> authorities = (List<String>) jsonObject.get("authorities");*/
            String s = parse.getPayload().toString();
            System.out.println(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}