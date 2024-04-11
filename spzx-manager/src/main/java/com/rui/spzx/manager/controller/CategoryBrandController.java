package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.CategoryBrandService;
import com.rui.spzx.model.dto.product.CategoryBrandDto;
import com.rui.spzx.model.entity.product.Brand;
import com.rui.spzx.model.entity.product.CategoryBrand;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: CategoryBrandController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 13:32
 * @version: 1.0
 */
@Tag(name = "品牌分类管理")
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    //分类品牌条件分页查询
    @GetMapping ("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page ,
                             @PathVariable Integer limit , CategoryBrandDto categoryBrandDto){
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }
    //添加
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //修改
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    // com.rui.spzx.manager.controller
//    加载品牌数据
//    需求：当用户选择了三级分类以后，此时需要将三级分类所对应的品牌数据查询出来在品牌下拉框中进行展示
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }

}
