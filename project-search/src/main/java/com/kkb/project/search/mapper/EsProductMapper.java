package com.kkb.project.search.mapper;

import com.kkb.project.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODO
 * @author: peng.ni
 * @date: 2021/04/26
 */
public interface EsProductMapper {
    /**
     * 获取指定ID的搜索商品
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
