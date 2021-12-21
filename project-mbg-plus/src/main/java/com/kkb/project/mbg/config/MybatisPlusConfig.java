package com.kkb.project.mbg.config;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Properties;

/**
 * @author lemon
 * 2021/04/13
 */
public class MybatisPlusConfig {

    private Properties properties;

    public MybatisPlusConfig(Properties properties){
        this.properties = properties;
    }

    /**
     * 数据源配置
     * @return
     */
    public DataSourceConfig dataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(properties.getProperty("url"));
        dataSourceConfig.setDriverName(properties.getProperty("driverClassName"));
        dataSourceConfig.setUsername(properties.getProperty("username"));
        dataSourceConfig.setPassword(properties.getProperty("password"));
        dataSourceConfig.setTypeConvert(new MysqlTypeConvertCustom());
        return dataSourceConfig;
    }

    /**
     * 包配置
     * @return
     */
    public PackageConfig packageConfig(){
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(properties.getProperty("package.parent"));
        packageConfig.setModuleName(properties.getProperty("package.moduleName"));
        packageConfig.setEntity(properties.getProperty("package.entity"));
        packageConfig.setController(properties.getProperty("package.controller"));
        packageConfig.setMapper(properties.getProperty("package.mapper"));
        packageConfig.setXml(properties.getProperty("package.xml"));
        return packageConfig;
    }

    /**
     * 策略配置
     * @return
     */
    public StrategyConfig strategyConfig(){
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setLogicDeleteFieldName("is_deleted");

        String tables = properties.getProperty("strategy.include");
        if (tables != null && tables.length() > 0){
            String[] includeTables = tables.trim().split(",");
            strategyConfig.setInclude(includeTables);
        }
        return strategyConfig;
    }

    /**
     * 全局配置
     * @return
     */
    public GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + properties.getProperty("globalConfig.outputDir"));
        globalConfig.setFileOverride(true);
        globalConfig.setSwagger2(true);
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        return globalConfig;
    }
}
