# 非常用注解快查表

| 注解                      | 作用                                                         |
| ------------------------- | ------------------------------------------------------------ |
| @ConditionalOnMissingBean | 保证相同类型的bean只有一个                                   |
| 以下为Api文档常用注解     |                                                              |
| @Api                      | 用于修饰Controller类                                         |
| @ApiOperation             | 用于修饰Controller类中的方法                                 |
| @ApiImplicitParam         | 修饰接口中的参数                                             |
| @ApiModelProperty         | 用于修饰实体类的属性                                         |
| @Validated                | （使用的是Hibernate Validator是SpringBoot内置的校验框架）校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理 |
| @NotEmpty                 | 被注释的属性不能为空                                         |
| @EqualsAndHashCode        | 自动的给model bean实现equals方法和hashcode方法。false表示的是只用自己的属性而不用父类的属性来生成hashcode |

# 配置类

## 数据库连接池配置

共有两处地方进行配置：

* config包下面的DruidConfig：
  * druidServlet：提供类的注册，配置ip黑白名单，帐号密码，是否能重置数据
  * filterRegistrationBean：配置url过滤规则，不需要过滤的资源
* yaml文件配置如下：

```xml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://8.140.125.207:3306/kkb?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: kkb
    password: kkb
    druid:
      initial-size: 5 #连接池初始化大小
      min-idle: 10 #最小空闲连接数
      max-active: 20 #最大连接数
```

## 全局跨域配置

* 前置知识：在配置前，需要弄清楚什么是跨域问题，为什么会出现跨域问题，解决框架是什么。请参考[前后端分离项目，如何解决跨域问题](https://gitee.com/peng_2ni/kkb-parent/wikis/%E5%89%8D%E5%90%8E%E7%AB%AF%E5%88%86%E7%A6%BB%E9%A1%B9%E7%9B%AE%EF%BC%8C%E5%A6%82%E4%BD%95%E8%A7%A3%E5%86%B3%E8%B7%A8%E5%9F%9F%E9%97%AE%E9%A2%98?sort_id=3919103)

## API接口文档配置

* 前置知识：参考[ kkb整合knife4j实现在线API文档](https://gitee.com/peng_2ni/kkb-parent/wikis/%E5%89%8D%E5%90%8E%E7%AB%AF%E5%88%86%E7%A6%BB%E9%A1%B9%E7%9B%AE%EF%BC%8C%E5%A6%82%E4%BD%95%E8%A7%A3%E5%86%B3%E8%B7%A8%E5%9F%9F%E9%97%AE%E9%A2%98?sort_id=3919103)
* knife4j接口文档地址：http://localhost:服务的端口号/doc.html  。如：http://localhost:8082/doc.html

在本项目中，我们需要在Knife4jConfig类下配置搜索的包名路径，标题，文档描述，联系人名称，是否要启用登录认证等信息。

在配置Knife4jConfig类前，我们需要编写knife4j的网关配置类（在project-knife4j-gateway包下）和Swagger基本配置类BaseSwaggerConfig（在project-common的com.kkb.project.common.config下）。并且Knife4jConfig需要继承BaseSwaggerConfig

## MybatisPlus配置

* 在分页插件方法中配置数据库格式
* 配置乐观锁

* 在yaml文件中配置

```xml
mybatis-plus:
  # 支持统配符 * 或者 ; 分割
  #配置枚举类扫描路径，会将路径下的枚举类进行注入
  typeEnumsPackage: com.kkb.project.domain.enums

  configuration:
    map-underscore-to-camel-case: true  #从数据库列名到java实体类的映射使用驼峰式命名
    auto-mapping-behavior: full  #对所有的 resultMap 都进行自动映射
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  #配置控制台打印完整带参数SQL语句

  mapper-locations: classpath*:mapper/**/*Mapper.xml   #配置mapper.xml的路径
  #MyBaits别名包扫描路径，通过该属性可以给包中的类注册别名，注册后在 Mapper对应的 XML文件
  #中可以直接使用类名，而不用使用全限定的类名(即 XML 中调用的时候不用包含包名）
  type-aliases-package: com.kkb.project.demo.domain
```

# 功能类

## Controller

controller层所需实现功能如下：

* 映射前端URI(@PostMapping("/xxx/xxx"))
* 标注api接口参数注解(@Api,@ApiOperation区别看快查表)
* 接收参数（@PathVariable，@RequestParam）
* 校验参数（@Validated）
* 调用service逻辑层，并返回成功的结果（已封装的结果实体类）给前端。（失败结果在Service使用断言处理）

### 示例demo

```java
package com.kkb.project.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.demo.domain.WorkType;
import com.kkb.project.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName DemoServiceImpl
 * @Author LZH
 * @Date 2021/4/26
 * @Description TODO 演示实现类类，不需要了请删除
 * @Version 1.0
 **/

@Api(value = "DemoController", tags = "admin演示接口")
@RestController
@RequestMapping("/admin/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * @Author 李梓豪
     * @Description 单一查找
     * @Date 2021年04月27日  15:04:41
     * @Param [id]
     * @return com.kkb.project.common.api.CommonResult
     * @Date Modify in 2021年04月27日  15:04:41
     * @Modify Content:
     **/
    @ApiOperation(value = "查找作品类型", notes = "通过ID来查找作品类型")
    @ApiImplicitParam(name = "id",value = "作品id",required = true,dataType = "Long")
    @PostMapping("/{id}")
    public CommonResult findWorkTypeById(@PathVariable Long id) {
        WorkType result = demoService.findWorkTypeById(id);
        return CommonResult.success(result, "查询成功");
    }

    /**
     * @Author 李梓豪
     * @Description 增加
     * @Date 2021年04月27日  15:04:51
    
     **/
    @ApiOperation(value = "新增作品类型", notes = "传入作品类型对象来新增作品")
    @ApiImplicitParam(name = "workType",value = "作品类型",required = true,dataType = "WorkType")
    @PostMapping("/insert")
    public CommonResult insertWorkType(@Validated WorkType workType) {
        demoService.insertWorkType(workType);
        return CommonResult.success(null, "新增成功");
    }

    /**
     * @Author 李梓豪
     * @Description  查找所有
     * @Date 2021年04月27日  15:04:48
     **/
    @ApiOperation(value = "查找所有作品", notes = "不需要传入参数")
    @PostMapping("/list")
    public CommonResult findAllWork() {
        List<WorkType> result = demoService.findAllWork();
        return CommonResult.success(result, "查找成功");
    }

    /**
     * @Author 李梓豪
     * @Description 分页显示
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "分页查询作品类型", notes = "分页查询默认从第一页开始每页显示5个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页数",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示数",required = true,dataType = "Integer")
    })
    @PostMapping("/page/list")
    public CommonResult pageFindWork(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<WorkType> result = demoService.pageFindWork(pageNum, pageSize);
        return CommonResult.success(result, "查找成功");
    }

    /**
     * @Author 李梓豪
     * @Description 删除
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "删除作品", notes = "通过id删除作品")
    @ApiImplicitParam(name = "id",value = "作品id",required = true,dataType = "Long")
    @PostMapping("/delete/{id}")
    public CommonResult deleteWorkTypeById(@PathVariable Long id) {
        demoService.deleteWorkTypeById(id);
        return CommonResult.success(null, "删除成功");
    }

    /**
     * @Author 李梓豪
     * @Description 修改
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "修改作品类型", notes = "传入作品类型对象进行修改")
    @ApiImplicitParam(name = "workType",value = "作品类型",required = true,dataType = "WorkType")
    @PostMapping("/update")
    public CommonResult updateWorkTypeById(WorkType workType) {
        demoService.updateWorkTypeById(workType);
        return CommonResult.success(null, "修改作品类型成功");
    }
}
```

## Service

Service层分为三个部分

* IBaseService接口及其实现类：分别供Service接口及其实现类继承，其中IBaseServiceImpl继承了ServiceImpl类，重点是，ServiceImpl类里面封装了BaseMapper的方法。这样我们在Service实现类里面编写代码时就不需要使用注解把dao注入到bean里面，较少重复的代码以及提供资源利用效率。
* Service接口
* Service实现类：主要使用断言做全局异常处理，提示失败结果。全局异常处理需要在project-common包下配置相关方法，请参考：[Springboot的全局异常处理方法](https://gitee.com/peng_2ni/kkb-parent/wikis/Springboot%E7%9A%84%E5%85%A8%E5%B1%80%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%96%B9%E6%B3%95?sort_id=3919111)

### 示例Demo

```java
@Service
public class DemoServiceImpl extends ServiceImpl<DemoDao, WorkType> implements DemoService {
    @Override
    public WorkType findWorkTypeById(Long id) {
        WorkType workType = this.getById(id);
        if (workType == null) {
            Asserts.fail("作品不存在");
        }
        return workType;
    }
   
}
```

## dao

dao接口直接继承BaseMapper即可

```java
public interface DemoDao extends BaseMapper<WorkType> {
}
```

## domain

* 注意：实体类需要实现序列化，因为实体类有可能需要持久化到session服务器中。参考：[实体类为什么要实现Serializable序列化的作用](https://blog.csdn.net/keehom/article/details/103029285)

```java
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "作品类型表实体类")
public class WorkType implements Serializable {

    @ApiModelProperty(value = "作品类型ID ", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作品类型 ", required = true)
    @NotEmpty(message = "作品类型不能为空")
    private String type;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    private static final long serialVersionUID = 1L;
}
```