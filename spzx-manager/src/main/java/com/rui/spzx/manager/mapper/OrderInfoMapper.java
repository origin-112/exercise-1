package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String createTime);
}
