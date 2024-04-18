package com.heima.trade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "交易下单表单实体")
public class OrderFormQO {
    @ApiModelProperty("收货地址id")
    private Long addressId;
    @ApiModelProperty("支付类型")
    private Integer paymentType;
    @ApiModelProperty("下单商品列表")
    private List<OrderDetailQO> details;
}
