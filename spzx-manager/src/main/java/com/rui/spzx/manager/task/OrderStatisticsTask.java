package com.rui.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.rui.spzx.manager.mapper.OrderInfoMapper;
import com.rui.spzx.manager.mapper.OrderStatisticsMapper;
import com.rui.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager
 * @className: OrderStatisticsTask
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/10 12:00
 * @version: 1.0
 */
@Component
@Slf4j
public class OrderStatisticsTask {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }

}
