package com.kkb.project.admin.controller;

import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.ProjectWorkStyle;
import com.kkb.project.admin.domain.ProjectWorkType;
import com.kkb.project.admin.domain.vo.ProjectVo;
import com.kkb.project.admin.domain.vo.ProjectWorkTypeAndStyleVo;
import com.kkb.project.admin.service.*;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.admin.domain.dto.ProjectAdminDto;
import com.kkb.project.admin.domain.dto.QueryProjectDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author liusheng
 * @file ProjectController.java
 * @email 747539062@qq.com
 * @Date 2021/4/20 18:08
 * @ModifiedBy qzj
 **/
@RestController
@RequestMapping("/project-admin")
@Api(value = "projectController",tags = "项目大厅API")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @Autowired
    private ProjectVoFacadeService projectResFacadeService;

    @Autowired
    private ProjectWorkStyleService projectWorkStyleService;

    @Autowired
    private ProjectWorkTypeService projectWorkTypeService;

    @Autowired
    private ProjectWorkTypeAndStyleFacadeService projectWorkTypeAndStyleFacadeService;

    @RequestMapping(value = "/list-projects",method = RequestMethod.GET)
    @ApiOperation(value = "查询项目列表", notes = "返回根据条件查询的项目列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回查询成功列表", response = CommonResult.class),
            @ApiResponse(code = 404, message = "参数检验失败", response = CommonResult.class),
            @ApiResponse(code = 401, message = "暂未登录或token已经过期", response = CommonResult.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "queryProjectDto", value = "项目查询参数", required = true, dataTypeClass = QueryProjectDto.class),
    })
    public CommonResult<CommonPage<ProjectVo>> getAllProjects(@NotNull QueryProjectDto queryProjectDto){
        CommonPage<ProjectVo> voList = projectResFacadeService.queryProjectList(queryProjectDto);
        return CommonResult.success(voList);
    }

    /**
     * 新建项目
     * @author  ynb
     * @param projectAdminDto
     * @return
     */
    @PostMapping("/add-project")
    @ApiOperation(value = "新建项目", notes = "返回是否新增成功")
    public CommonResult<?> addNewProject(@NotNull ProjectAdminDto projectAdminDto) {
        projectService.addNewProject(projectAdminDto);

        return CommonResult.success(null,"新建项目成功");
    }

    @GetMapping("/update-project/{id}")
    @ApiOperation(value = "修改项目",notes = "返回是否修改成功")
    public CommonResult<Project> updateProject(@NotNull ProjectAdminDto projectAdminDto, @PathVariable("id") long projectId) {
        projectService.updateProject(projectAdminDto, projectId);
        return CommonResult.success(null,"修改项目成功");
    }

    @PostMapping("/list-work-style")
    @ApiOperation(value = "查询所有的工作方式")
    public CommonResult<Map<Long, ProjectWorkStyle>> getAllWorkStyles() {
        Map<Long, ProjectWorkStyle> allWorkStyles = projectWorkStyleService.getAllWorkStyles();
        return CommonResult.success(allWorkStyles);
    }

    @PostMapping("/list-work-type")
    @ApiOperation(value = "查询所有项目类型")
    public CommonResult<Map<Long, ProjectWorkType>> getAllWorkTypes() {
        Map<Long, ProjectWorkType> allWorkTypes = projectWorkTypeService.getAllWorkTypes();
        return CommonResult.success(allWorkTypes);
    }

    @PostMapping("/delete-project/{id}")
    @ApiOperation(value = "删除项目", notes = "根据id删除项目")
    @ApiImplicitParam(name = "id", value = "项目ID", required = true, paramType = "Long")
    public CommonResult<?> deleteProject(@PathVariable("id") Long projectId) {
        projectService.deleteProjectById(projectId);
        return CommonResult.success(null,"删除项目成功");
    }

    @PostMapping("/list-work-style-and-type")
    @ApiOperation(value = "查询所有工作方式和类型")
    public CommonResult<ProjectWorkTypeAndStyleVo> getAllWorkStyleAndTypes() {
        ProjectWorkTypeAndStyleVo allTypesAndStyles = projectWorkTypeAndStyleFacadeService.getAll();
        return CommonResult.success(allTypesAndStyles, "查询项目所有类型和工作方式成功");
    }
}
