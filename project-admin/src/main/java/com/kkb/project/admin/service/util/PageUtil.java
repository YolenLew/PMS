package com.kkb.project.admin.service.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kkb.project.common.api.CommonPage;

import java.util.List;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * Page 相关的工具函数,
 * 声明为enum 防止实例化对象
 */
public enum  PageUtil {
    ;
    public static <T> CommonPage<T> wrapToCommonPage(List<T> list, IPage<?> pageInfo) {
        if (pageInfo == null) {
            return CommonPage.restPage(list);
        }
        CommonPage<T> commonPage = new CommonPage<>();
        commonPage.setPageNum((int) pageInfo.getCurrent());
        commonPage.setPageSize((int) pageInfo.getSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setTotalPage((int) pageInfo.getPages());
        commonPage.setList(list);
        return commonPage;
    }
}
