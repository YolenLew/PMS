package com.kkb.project.portal.controller;

import cn.hutool.core.util.ObjectUtil;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * *
 * @Author lemon
 * @Date 2021/4/17
 * @Description 文件上传
 * @Version 1.0
 *
 */
@RestController
@RequestMapping("/minio")
@Validated
@Api(value = "FileUploadController", tags = "minio文件上传")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @ApiOperation(value = "文件上传", notes = "返回可访问地址")
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiImplicitParam(name = "file", value = "上传的文件", required = true, dataType = "MultipartFile")
    public CommonResult<String> uploadFile(@RequestParam("file") MultipartFile file){
        String address = fileUploadService.uploadFile(file);
        if (ObjectUtil.isEmpty(address)){
            return CommonResult.failed("文件上传失败");
        }
        return CommonResult.success(address);
    }
}
