package com.heima.pay.controller;

import com.heima.pay.IPayOrderService;
import com.heima.pay.api.PayApi;
import com.heima.pay.enums.PayType;
import com.heima.pay.request.PayApplyQO;
import com.heima.pay.request.PayOrderFormQO;
import com.heima.common.exception.BizIllegalException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PayController implements PayApi {

    private final IPayOrderService payOrderService;


    public String applyPayOrder(@RequestBody PayApplyQO applyDTO){
        if(!PayType.BALANCE.equalsValue(applyDTO.getPayType())){
            // 目前只支持余额支付
            throw new BizIllegalException("抱歉，目前只支持余额支付");
        }
        return payOrderService.applyPayOrder(applyDTO);
    }


    public void tryPayOrderByBalance(@PathVariable("id") Long id, @RequestBody PayOrderFormQO payOrderFormDTO){
        payOrderFormDTO.setId(id);
        payOrderService.tryPayOrderByBalance(payOrderFormDTO);
    }
}
