package com.kkb.project.mbg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.kkb.project.mbg.config.MybatisPlusConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lemon
 * 2021/04/13
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        Properties properties = new Properties();
        try {
            properties.load(MybatisPlusGenerator.class.getResourceAsStream("/mybatis-plus.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MybatisPlusConfig mybatisPlusConfig = new MybatisPlusConfig(properties);
        mpg.setDataSource(mybatisPlusConfig.dataSourceConfig());
        mpg.setGlobalConfig(mybatisPlusConfig.globalConfig());
        mpg.setPackageInfo(mybatisPlusConfig.packageConfig());
        mpg.setStrategy(mybatisPlusConfig.strategyConfig());
        mpg.execute();
    }
}
