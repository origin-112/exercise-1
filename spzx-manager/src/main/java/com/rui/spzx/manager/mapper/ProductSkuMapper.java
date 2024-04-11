package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    void save(ProductSku productSku);

    void updateById(ProductSku productSku);

    List<ProductSku> selectByProductId(Long id);

    void deleteByProductId(Long id);
}
