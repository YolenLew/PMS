package com.kkb.project.auth.extension.sms;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.kkb.project.auth.service.UserRegisterService;
import com.kkb.project.auth.service.impl.UserDetailServiceImpl;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.common.exception.ApiException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import sun.security.util.SecurityConstants;

import java.util.HashSet;

/**
 * 短信验证码认证授权提供者
 *  Provider 从数据库获取用户认证信息和客户端请求传值的用户信息进行认证密码判读，验证通过之后返回token给客户端
 *
 * @author Yolen
 * @date 2021/12/23
 */
@Data
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String code = (String) authenticationToken.getCredentials();
        log.info("SmsCodeAuthenticationProvider.authenticate gets mobile:{} and code:{}", mobile, code);
        String codeKey = AuthConstant.SMS_AUTH_CODE_KEY_PREFIX + mobile;
        Object correctCode = redisTemplate.opsForValue().get(codeKey);
        // 验证码比对
        if (ObjectUtil.isNull(correctCode) || !correctCode.toString().equals(code)) {
            throw new ApiException("验证码不正确");
        }
        // 比对成功删除缓存的验证码
        redisTemplate.delete(codeKey);
        UserDetails userDetails = ((UserDetailServiceImpl) userDetailsService).loadUserByPhone(mobile);
        SmsCodeAuthenticationToken result = new SmsCodeAuthenticationToken(userDetails, authentication.getCredentials(), new HashSet<>());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
