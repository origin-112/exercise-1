package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.OrderInfoService;
import com.rui.spzx.model.dto.order.OrderStatisticsDto;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.model.vo.order.OrderStatisticsVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: OrderInfoController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/4/10 16:04
 * @version: 1.0
 */
@RestController
@Tag(name = "订单管理")
@RequestMapping(value = "/admin/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService ;

    @GetMapping("/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto) ;
        return Result.build(orderStatisticsVo , ResultCodeEnum.SUCCESS) ;
    }
}
