package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.ProductService;
import com.rui.spzx.model.dto.product.ProductDto;
import com.rui.spzx.model.entity.product.Product;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: ProductController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 22:27
 * @version: 1.0
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping(value = "/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService ;

    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    //保存商品数据
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //当用户点击修改按钮的时候，那么此时就弹出对话框，
    //在该对话框的商品表单中回显商品相关数据，此时用户对商品数据进行修改，修改完毕以后点击提交按钮将表单进行提交，后端服务修改数据库中数据即可。
//    根据id查询商品详情
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS) ;
    }
    //保存修改商品数据
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //当点击审核按钮的时候此时需要弹出一个对话框，在该对话框中展示商品的详情信息，用户可以在该对话框中点击通过或者驳回按钮对商品进行审核操作。
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
//    当用户点击上架按钮的时候对商品进行上架操作，点击下架按钮的时候对商品进行下架操作。
//    实现思路：更改商品的上下架状态
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
