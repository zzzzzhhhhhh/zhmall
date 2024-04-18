package com.heima.pay;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.pay.dataobject.PayOrder;
import com.heima.pay.request.PayApplyQO;
import com.heima.pay.request.PayOrderFormQO;

/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyQO applyDTO);

    void tryPayOrderByBalance(PayOrderFormQO payOrderFormDTO);
}
