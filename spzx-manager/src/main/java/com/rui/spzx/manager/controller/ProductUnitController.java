package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.ProductUnitService;
import com.rui.spzx.model.entity.base.ProductUnit;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: ProductUnitController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/3 15:04
 * @version: 1.0
 */
@Tag(name = "商品单元管理")
@RestController
@RequestMapping(value = "/admin/product/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductUnitService productUnitService;

    @GetMapping("findAll")
    public Result<List<ProductUnit>> findAll() {
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList , ResultCodeEnum.SUCCESS) ;
    }
}
