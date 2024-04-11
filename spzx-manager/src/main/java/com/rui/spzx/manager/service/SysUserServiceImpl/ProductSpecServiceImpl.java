package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.mapper.ProductSpecMapper;
import com.rui.spzx.manager.service.ProductSpecService;
import com.rui.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: ProductSpecServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 21:24
 * @version: 1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper ;

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page , limit) ;
        List<ProductSpec> productSpecList = productSpecMapper.findByPage() ;
        return new PageInfo<>(productSpecList);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec) ;
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
