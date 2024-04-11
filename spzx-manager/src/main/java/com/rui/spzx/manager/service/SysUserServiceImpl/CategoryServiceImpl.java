package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.alibaba.excel.EasyExcel;
import com.rui.spzx.common.exception.RuiException;
import com.rui.spzx.manager.listener.ExcelListener;
import com.rui.spzx.manager.mapper.CategoryMapper;
import com.rui.spzx.manager.service.CategoryService;
import com.rui.spzx.model.entity.product.Category;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: CategoryServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/3/29 16:27
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findCategoryList(Long parentId) {
        //1 根据 id 条件进行查询，返回list集合
        //select * from category where parent_id = 0 ;
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(parentId);
        //2 遍历返回list集合
//        判断每个分类是否有下一层分类，更新设置 “hasChildren” = true
        if (!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                int count = categoryMapper.selectCountByParentId(category.getId());
                if (count > 0){
                    category.setHasChildren(true);
                }else {
                    category.setHasChildren(false);
                }
            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {

        try {

            //1 设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            //2 查询数据库中的数据，查询所有分类，返回list
            List<Category> categoryList = categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());

            // 将从数据库中查询到的Category对象转换成CategoryExcelVo对象
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }

            // 写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuiException(ResultCodeEnum.DATA_ERROR);
        }
    }

    //导入
    @Override
    public void importData(MultipartFile file) {
        try {
            //创建监听器对象，传递mapper对象
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);
            //调用read方法读取excel数据
            EasyExcel.read(file.getInputStream(),
                    CategoryExcelVo.class,
                    excelListener).sheet().doRead();
        } catch (IOException e) {
            throw new RuiException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
