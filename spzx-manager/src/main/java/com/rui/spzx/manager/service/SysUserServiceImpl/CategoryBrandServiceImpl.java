package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.mapper.CategoryBrandMapper;
import com.rui.spzx.manager.service.CategoryBrandService;
import com.rui.spzx.model.dto.product.CategoryBrandDto;
import com.rui.spzx.model.entity.product.Brand;
import com.rui.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: CategoryBrandServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 13:34
 * @version: 1.0
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    //分类品牌条件分页查询
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page , limit);
        List<CategoryBrand> list = categoryBrandMapper.findByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //添加
    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand) ;
    }

    //修改
    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand) ;
    }

    //删除
    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id) ;
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {

        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }
}
