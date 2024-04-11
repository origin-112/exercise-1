package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.dto.product.CategoryBrandDto;
import com.rui.spzx.model.entity.product.Brand;
import com.rui.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    //分类品牌条件分页查询
    List<CategoryBrand> findByPage( CategoryBrandDto categoryBrandDto);
    //添加
    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
