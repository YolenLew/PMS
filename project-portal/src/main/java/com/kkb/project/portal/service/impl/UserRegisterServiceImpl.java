package com.kkb.project.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.common.service.RedisService;
import com.kkb.project.portal.config.OAuth2ClientProperties;
import com.kkb.project.portal.dao.UserRegisterMapper;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 用户注册服务实现类
 *
 * @author Yolen
 * @since 2021-04-29
 */
@Slf4j
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserRegisterMapper, UserRegister> implements UserRegisterService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserSkillService userSkillService;
    @Autowired
    private UserExperienceService userExperienceService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    /**
     * 用户注册
     *
     * @param phone    手机号
     * @param password 密码
     */
    @Override
    public void register(String phone, String password) {
        // 查重
        List<UserRegister> registerList = this.list(Wrappers.<UserRegister>lambdaQuery().eq(UserRegister::getPhone, phone));
        if (CollectionUtil.isNotEmpty(registerList)) {
            Asserts.fail(StrUtil.format("【{}】用户已注册", phone));
        }
        // 注册用户信息
        UserRegister register = new UserRegister();
        register.setPhone(phone);
        register.setPassword(BCrypt.hashpw(password));
        register.setUsername(phone);
        boolean registerResult = this.save(register);
        if (!registerResult) {
            Asserts.fail(StrUtil.format("【{}】用户注册失败", phone));
        }
        // 同步新增至user表
        User user = new User();
        user.setLoginId(register.getId());
        boolean userSaveResult = userService.saveOrUpdate(user);
        if (!userSaveResult) {
            Asserts.fail(StrUtil.format("【{}】用户名人堂信息保存失败", phone));
        }
    }

    /**
     * 密码登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return 登录用户
     */
    @Override
    public Oauth2TokenDTO login(String phone, String password) {
        UserRegister userByPhone = this.getOne(Wrappers.<UserRegister>lambdaQuery().eq(UserRegister::getPhone, phone));
        if (ObjectUtil.isNull(userByPhone)) {
            Asserts.fail(StrUtil.format("【{}】用户不存在", phone));
        }
        // 密码校验
        boolean checkPassword = BCrypt.checkpw(password, userByPhone.getPassword());
        if (!checkPassword) {
            Asserts.fail("用户名或密码错误");
        }
        // 申请令牌
        CommonResult<Oauth2TokenDTO> commonResult = getToken(phone, password);
        if (ObjectUtil.isNull(commonResult.getData())) {
            Asserts.fail("令牌申请失败");
        }
        return commonResult.getData();
    }

    /**
     * 验证码登录
     *
     * @param phone    手机号
     * @param authCode 验证码
     * @return 登录用户
     */
    @Override
    public Oauth2TokenDTO loginByCode(String phone, String authCode) {
        // 验证码校验
        boolean verifyAuthCode = verifyAuthCode(authCode, phone);
        if (!verifyAuthCode) {
            Asserts.fail("验证码错误");
        }
        // 删除验证码
        redisService.del(AuthConstant.SMS_AUTH_CODE_KEY_PREFIX + phone);
        List<UserRegister> registerList = this.list(Wrappers.<UserRegister>lambdaQuery().eq(UserRegister::getPhone, phone));
        if (CollectionUtil.isEmpty(registerList)) {
            Asserts.fail(StrUtil.format("【{}】用户不存在", phone));
        }
        // 申请令牌
        UserRegister userRegister = registerList.get(0);
        phone = AuthConstant.SMS_LOGIN_TYPE_PREFIX + phone;
        CommonResult<Oauth2TokenDTO> commonResult = getToken(phone, userRegister.getPassword());
        if (ObjectUtil.isNull(commonResult.getData())) {
            Asserts.fail("令牌申请失败");
        }
        return commonResult.getData();
    }

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 验证码
     */
    @Override
    public String getCode(String phone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        // ums:authCode 90s
        redisService.set(AuthConstant.SMS_AUTH_CODE_KEY_PREFIX + phone, sb.toString(), 1000);
        return sb.toString();
    }

    /**
     * 获取当前登录用户信息
     *
     * @param loginId 注册用户id
     * @return 当前登录用户
     */
    @Override
    public UserInfo getUser(Long loginId) {
        // TODO 从令牌中提取用户id
        UserRegister register = this.getById(loginId);
        if (ObjectUtil.isNull(register)) {
            Asserts.fail("用户不存在");
        }
        // 查询用户详情
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getLoginId, loginId));
        // 查询技能
        List<UserSkill> userSkillList = userSkillService.list(Wrappers.<UserSkill>lambdaQuery().eq(UserSkill::getUserId, user.getId()));
        // 查询项目
        List<UserExperience> userExperienceList = userExperienceService.list(Wrappers.<UserExperience>lambdaQuery().eq(UserExperience::getUserId, user.getId()));
        // 封装用户信息
        UserInfo userInfo = BeanUtil.copyProperties(user, UserInfo.class);
        userInfo.setPhone(register.getPhone());
        List<String> skillList = new ArrayList<>();
        List<String> experienceList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(userSkillList)) {
            userSkillList.forEach(userSkill -> skillList.add(userSkill.getSkillDesc()));
        }
        if (CollectionUtil.isNotEmpty(userExperienceList)) {
            userExperienceList.forEach(userExperience -> experienceList.add(userExperience.getProjectExpDesc()));
        }
        userInfo.setSkillList(skillList);
        userInfo.setExperienceList(experienceList);
        return userInfo;
    }

    /**
     * 验证码校验
     *
     * @param authCode 验证码
     * @param phone    手机号
     * @return 校验结果
     */
    private boolean verifyAuthCode(String authCode, String phone) {
        if (StrUtil.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = (String) redisService.get(AuthConstant.SMS_AUTH_CODE_KEY_PREFIX + phone);
        return authCode.equals(realAuthCode);
    }

    /**
     * 申请令牌
     *
     * @param phone    手机号
     * @param password 密码
     * @return 令牌申请结果
     */
    private CommonResult<Oauth2TokenDTO> getToken(String phone, String password) {
        // 封装请求参数
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("client_id", Collections.singletonList(oAuth2ClientProperties.getClientId()));
        parameters.put("client_secret",Collections.singletonList(oAuth2ClientProperties.getClientSecret()));
        parameters.put("grant_type",Collections.singletonList(oAuth2ClientProperties.getGrantType()));
        parameters.put("username",Collections.singletonList(phone));
        parameters.put("password",Collections.singletonList(password));
        HttpEntity entity = new HttpEntity(parameters, null);
        ParameterizedTypeReference<CommonResult<Oauth2TokenDTO>> typeRef = new ParameterizedTypeReference<CommonResult<Oauth2TokenDTO>>(){};
        ResponseEntity<CommonResult<Oauth2TokenDTO>> exchange = restTemplate.exchange(oAuth2ClientProperties.getAccessTokenUri(), HttpMethod.POST, entity, typeRef);
        return exchange.getBody();
    }
}
