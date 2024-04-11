package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.ProductSpecService;
import com.rui.spzx.model.entity.product.ProductSpec;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: ProductSpecController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 21:22
 * @version: 1.0
 */
@Tag(name = "商品规格管理")
@RestController
@RequestMapping(value = "/admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    private ProductSpecService productSpecService ;

    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
//    加载商品规格数据
//    需求：当添加商品的表单对话框展示出来以后，此时就需要从数据库中查询出来所有的商品规格数据，并将查询到的商品规格数据在商品规格下拉框中进行展示。
    @GetMapping("findAll")
    public Result findAll() {
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
}
