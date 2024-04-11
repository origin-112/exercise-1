package com.rui.spzx.manager.service;

import com.rui.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    //分类列表，每次查询一层数据
    List<Category> findCategoryList(Long parentId);
    //导出功能
    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
