package com.rui.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.model.dto.product.CategoryBrandDto;
import com.rui.spzx.model.entity.product.Brand;
import com.rui.spzx.model.entity.product.CategoryBrand;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface CategoryBrandService {
    //分类品牌条件分页查询
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    //添加
    void save(CategoryBrand categoryBrand);
    //修改
    void updateById(CategoryBrand categoryBrand);
    //删除
    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
