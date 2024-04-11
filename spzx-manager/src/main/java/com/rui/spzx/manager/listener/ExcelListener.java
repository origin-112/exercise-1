package com.rui.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.rui.spzx.manager.mapper.CategoryMapper;
import com.rui.spzx.model.entity.product.Category;
import com.rui.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.listener
 * @className: ExcelListener
 * @author: liuzr
 * @description: TODO
 * @date: 2024/3/31 20:40
 * @version: 1.0
 */
public class ExcelListener <T> implements ReadListener<T> {
    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //构造传递mapper，操作数据库
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }
    //从第二行开始读取，把每行读取内容封装到T对象里面
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        CategoryExcelVo data = (CategoryExcelVo)t;
        //把每行数据对象t 放到cachedDataList集合里面
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(cachedDataList.size() >= BATCH_COUNT){
            // 存储完成清理 list
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    //
    private void saveData() {
        categoryMapper.batchInsert(cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel解析完毕以后需要执行的代码
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();

    }
}
