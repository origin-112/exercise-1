package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.product.Category;
import com.rui.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //1 根据 id 条件进行查询，返回list集合
    public List<Category> selectCategoryByParentId(Long parentId) ;

    public int selectCountByParentId(Long parentId);

    List<Category> selectAll();

    //导入功能的批量保存
    void batchInsert(List<CategoryExcelVo> categoryList);
}
