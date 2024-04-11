package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.dto.product.ProductDto;
import com.rui.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findByPage(ProductDto productDto);

    void save(Product product);

    void updateById(Product product);

    Product selectById(Long id);

    void deleteById(Long id);
}
