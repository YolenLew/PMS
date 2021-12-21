package com.kkb.project.auth.config;

import cn.hutool.json.JSONUtil;
import com.kkb.project.common.constant.AuthConstant;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;


import java.security.KeyPair;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("secret");
        System.out.println(encode);
    }

    @Test
    public void test2() {
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("kaikeba.jks"), "kaikeba".toCharArray());
        KeyPair key = keyFactory.getKeyPair("kaikeba", "kaikeba".toCharArray());
        RSAPrivateCrtKey privateCrtKey = (RSAPrivateCrtKey) key.getPrivate();
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", 123);
        tokenMap.put("name", "mickey");
        tokenMap.put("roles", "r01, r02, admin");
        Jwt jwt = JwtHelper.encode(JSONUtil.toJsonStr(tokenMap), new RsaSigner(privateCrtKey));
        String token = jwt.getEncoded();
        System.out.println(token);
    }

    @Test
    public void test3(){
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTk1MTY0MjEsInVzZXJfbmFtZSI6ImxlbW9uIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9hZG1pbiJdLCJqdGkiOiJkNzdkMTI0OS1kZDVmLTRmNTQtYWU0ZS1iMWVmODk4ZTI1NDMiLCJjbGllbnRfaWQiOiJra2IiLCJzY29wZSI6WyJhbGwiXX0.VXBSiIFw-y8ioYvwIaA9_bAj3O3mqmwvyUnKld8maXAFRMCubPf4m-oFMhWzwFVrMmtUUo6CXUuqiTvgX71DaM-65THkwCoNKgvf_CBbSfVrRjoWjEGlwUUWjjGU5NUHwNAwmhOGZ3iNMkgQh_cDs3cOe5dcHy5EbEjWQXqXsn1iL-24Sqb0orpgrQPdA_nhTuUkfWNzi8mNDAsYmX_9YKvSw-mu1gHHwrACDbo1s8GOJNoXWxzjZN58YSLEGtX950zu5oWL46r-KQy6ZFIAGoDe0i5JAccb-Xpmrtl2fJJwtpiJyOQWDbYV7qVrnW_0JioAGBX0zfbpOEcz_H8rHw";

        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiugnZbEQd2ZTiZnjVHyf\n" +
                "VQ8IbK181MfQxHq0URDMnz7qV1N1nxx9rSlUVwibEnPplqB9mt2xTTJTPgC4G+fn\n" +
                "grB7gRHPauaG+jyBnRAUTXKZltDtQfipfZaVZyPG2qUolAWezCUW7y3bfXbNz4mX\n" +
                "hvUUUggcn+z3DgFVhwH0zjhoivJHzP4N2OUNrUWHvUvsdvb50nWazTXp70MFqyj9\n" +
                "8auNlQAUFbaezIWDGKf0M2IfpRahDmwuTYiqKVFr7rf1hZ/uL/3R8qsb17eU8OHB\n" +
                "qBM/CB+ZtA4iF12pzvX9Qy1LnwHhPyIZvEZcr1R9JkZVECRaHxYcECp4/p4RtlJ0\n" +
                "IQIDAQAB\n" +
                "-----END PUBLIC KEY-----";

        try {
            JWSObject parse = JWSObject.parse(token);
            Payload payload = parse.getPayload();
            System.out.println(payload.toString());
            //JWSVerifier jwsVerifier = new RSASSAVerifier();
            //boolean b = parse.verify(jwsVerifier);
            //System.out.println(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJzdWIiOiJsZW1vbiIsImlhdCI6MTYxOTU4MDc3MH0.Qk7nvyRqtMTHgK-QFehtfHgu0i6O3KTJl9rcN-a0AJk";
        Jwt jwt = JwtHelper.decodeAndVerify(token1, new RsaVerifier(publicKey));
        String claims = jwt.getClaims();
        System.out.println(claims);
    }

    @Test
    public void test4(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("111")
                .setSubject("lemon")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "kaikeba");
        System.out.println(jwtBuilder.compact());
    }

    @Test
    public void test5(){
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        for (Map.Entry<Object, Object> entry : entries.entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}