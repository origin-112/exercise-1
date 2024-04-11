package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.dto.order.OrderStatisticsDto;
import com.rui.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {

    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
