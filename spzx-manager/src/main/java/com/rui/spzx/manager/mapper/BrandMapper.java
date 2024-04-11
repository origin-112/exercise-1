package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    //列表
    List<Brand> findByPage();
    //添加
    void save(Brand brand);
    //修改
    void updateById(Brand brand);
    //删除
    void deleteById(Long id);
    //查询所有品牌
    List<Brand> findAll();
}
