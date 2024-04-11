package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.rui.spzx.manager.mapper.ProductUnitMapper;
import com.rui.spzx.manager.service.ProductUnitService;
import com.rui.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: ProductUnitServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/3 15:05
 * @version: 1.0
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper ;
    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
