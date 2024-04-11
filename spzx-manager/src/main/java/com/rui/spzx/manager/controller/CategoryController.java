package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.CategoryService;
import com.rui.spzx.model.entity.product.Category;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: CategoryController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/3/29 16:25
 * @version: 1.0
 */
@Tag(name = "商品分类")
@RestController
@RequestMapping(value = "/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //分类列表，每次查询一层数据
    //select * from category where parent_id = 0 ;
    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/findCategoryList/{parentId}")
    public Result findCategoryList(@PathVariable Long parentId){
        List<Category> list = categoryService.findCategoryList(parentId);
        return Result.build(list , ResultCodeEnum.SUCCESS);
    }
    //导出功能
    @Operation(summary = "导出分类Excel表")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }
    //导入功能
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
