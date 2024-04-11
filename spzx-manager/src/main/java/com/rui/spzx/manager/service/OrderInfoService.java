package com.rui.spzx.manager.service;

import com.rui.spzx.model.dto.order.OrderStatisticsDto;
import com.rui.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
