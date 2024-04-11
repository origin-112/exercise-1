package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findAll();
}
