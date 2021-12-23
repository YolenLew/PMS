package com.kkb.project.auth.config;

import org.junit.Test;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * @author Yolen
 * @date 2021/12/21
 */
public class AnyTest {

    @Test
    public void test00() {

    }

    @Test
    public void testStringToCollection() {
        List<String> stringList = Collections.singletonList("sms,password");
        stringList.forEach(System.out::println);
    }

    @Test
    public void testBase64() {
        String appId = new String(Base64.getDecoder().decode("bWFsbC1hcHA6MTIzNDU2"));
        String pcId = new String(Base64.getDecoder().decode("bWFsbC1hZG1pbi13ZWI6MTIzNDU2"));
        System.out.println("appId:" + appId);
        System.out.println("pcId:" + pcId);
    }

}
