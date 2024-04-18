package com.heima.trade.api;

import com.heima.trade.TradeBaseApi;
import com.heima.trade.request.OrderFormQO;
import com.heima.trade.request.OrderQO;
import com.heima.trade.response.OrderVO;
import feign.Param;
import org.springframework.web.bind.annotation.*;

public interface TradeApi extends TradeBaseApi {

    String TRADE_BASE_URL="/orders";

    @GetMapping(TRADE_BASE_URL+"/{id}")
    OrderVO queryOrderById(@PathVariable("id") Long orderId);

    @PostMapping(TRADE_BASE_URL)
    Long createOrder(@RequestBody OrderFormQO orderFormDTO);

    @PutMapping(TRADE_BASE_URL+"/{orderId}")
    void markOrderPaySuccess(@PathVariable("orderId") Long orderId);

    @PostMapping(TRADE_BASE_URL+"/updateById")
    void updateOrderById(@RequestBody OrderQO orderQO);
}
