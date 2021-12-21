package com.kkb.project.admin.controller;

import com.kkb.project.admin.domain.CarouselImageGroup;
import com.kkb.project.admin.service.CarouselService;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName FameController
 * @Author lzh
 * @Date 2021/4/21 20:00
 * @Description 轮播图管理页面
 * @Version 2.0
 **/
@Api(value = "AdminCarouselImageGroup", tags = "轮播图管理页面")
@RestController
@RequestMapping("/admin/carousel")
public class CarouselImageGroupController {

    @Autowired
    private CarouselService carouselService;


    @ApiOperation(value = "数据获取", notes = "根据分组对轮播图的数据进行获取")
    @ApiImplicitParam(name = "imageGroup", value = "轮播图组别", required = true, dataType = "Integer")
    @PostMapping("/find/image/{imageGroup}")
    public CommonResult<List<CarouselImageGroup>> findImageByGroupId(@PathVariable Integer imageGroup) {
        List<CarouselImageGroup> result = carouselService.findImageByGroupId(imageGroup);
        return CommonResult.success(result, "获取轮播图数据成功");
    }

    @ApiOperation(value = "新增栏位", notes = "传入参数进行新增轮播图栏位")
    @ApiImplicitParam(name = "imageGroup", value = "轮播图组别", required = true, dataType = "Integer")
    @PostMapping("/add/carousel/{imageGroup}")
    public CommonResult<Integer> addCarousel(@PathVariable Integer imageGroup) {
        CarouselImageGroup carouselImageGroup = new CarouselImageGroup();
        carouselImageGroup.setImageGroup(imageGroup);
        carouselService.addCarousel(carouselImageGroup);
        return CommonResult.success(null, "新增成功");
    }

    @ApiOperation(value = "上传轮播图", notes = "根据图片ID和图片位置上传轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图片id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "imageUri", value = "图片位置", required = true, dataType = "String")
    })
    @PostMapping("/upload/image")
    public CommonResult<Integer> uploadImage(String imageUri, Long id) {
        carouselService.uploadImage(imageUri, id);
        return CommonResult.success(null, "上传成功");
    }

    @ApiOperation(value = "删除图片和栏位", notes = "根据传入的图片ID删除轮播图和栏位")
    @ApiImplicitParam(name = "id", value = "图片id", required = true, dataType = "Long")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteById(@PathVariable Long id) {
        carouselService.deleteById(id);
        return CommonResult.success(null, "删除栏位成功");
    }

}