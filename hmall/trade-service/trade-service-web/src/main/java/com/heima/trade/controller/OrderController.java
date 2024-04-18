package com.heima.trade.controller;

import com.heima.trade.IOrderService;
import com.heima.trade.api.TradeApi;
import com.heima.trade.dataobject.Order;
import com.heima.trade.request.OrderFormQO;
import com.heima.trade.request.OrderQO;
import com.heima.trade.response.OrderVO;
import com.hmall.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController implements TradeApi {
    private final IOrderService orderService;

    public OrderVO queryOrderById(@Param ("订单id")@PathVariable("id") Long orderId) {
        return BeanUtils.copyBean(orderService.getById(orderId), OrderVO.class);
    }


    public Long createOrder(@RequestBody OrderFormQO orderFormDTO){
        return orderService.createOrder(orderFormDTO);
    }

    public void markOrderPaySuccess(@PathVariable("orderId") Long orderId) {
        orderService.markOrderPaySuccess(orderId);
    }

    @Override
    public void updateOrderById(OrderQO orderQO) {
        orderService.updateById(BeanUtils.copyBean(orderQO, Order.class));
    }
}
