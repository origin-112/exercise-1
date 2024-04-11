package com.rui.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.model.dto.product.ProductDto;
import com.rui.spzx.model.entity.product.Product;

public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    void updateById(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
