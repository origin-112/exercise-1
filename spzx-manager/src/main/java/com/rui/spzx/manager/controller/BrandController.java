package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.BrandService;
import com.rui.spzx.model.entity.product.Brand;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: BrandController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 8:31
 * @version: 1.0
 */
@Tag(name = "品牌管理")
@RestController
@RequestMapping(value = "/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    //查询所有品牌
    @Operation(summary = "查询所有品牌")
    @GetMapping("/findAll")
    public Result findAll(){
        List<Brand> list = brandService.findAll();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page ,
                       @PathVariable Integer limit){
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }

    //添加
    @Operation(summary = "添加")
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
    //修改
    @Operation(summary = "修改")
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //删除
    @Operation(summary = "删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
