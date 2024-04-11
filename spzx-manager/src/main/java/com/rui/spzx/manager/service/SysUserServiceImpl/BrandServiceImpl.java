package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.mapper.BrandMapper;
import com.rui.spzx.manager.service.BrandService;
import com.rui.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: BrandServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/1 8:33
 * @version: 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    //列表
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page , limit);
        List<Brand> list  = brandMapper.findByPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //添加
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand) ;
    }

    //修改
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand) ;
    }

    //删除
    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id) ;
    }

    //查询所有品牌
    @Override
    public List<Brand> findAll() {
        List<Brand> list = brandMapper.findAll();
        return list;
    }
}
