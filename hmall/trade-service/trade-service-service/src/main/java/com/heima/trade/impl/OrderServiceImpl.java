package com.heima.trade.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.cart.api.api.CartApi;
import com.heima.item.api.api.ItemApi;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.heima.trade.IOrderDetailService;
import com.heima.trade.IOrderService;
import com.heima.trade.dataobject.Order;
import com.heima.trade.dataobject.OrderDetail;
import com.heima.trade.mapper.OrderMapper;
import com.heima.trade.request.OrderDetailQO;
import com.heima.trade.request.OrderFormQO;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final ItemApi itemApi;

    private final IOrderDetailService detailService;

    private final CartApi cartApi;

    @Override
    @Transactional
    public Long createOrder(OrderFormQO orderFormDTO) {
        // 1.订单数据
        Order order = new Order();
        // 1.1.查询商品
        List<OrderDetailQO> detailDTOS = orderFormDTO.getDetails();
        // 1.2.获取商品id和数量的Map
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailQO::getItemId, OrderDetailQO::getNum));
        List<Long> itemIds = itemNumMap.keySet().stream().collect(Collectors.toList());
        // 1.3.查询商品
        List<ItemResp> items = itemApi.queryItemByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("商品不存在");
        }
        // 1.4.基于商品价格、购买数量计算商品总价：totalFee
        int total = 0;
        for (ItemResp item : items) {
            total += item.getPrice() * itemNumMap.get(item.getId());
        }
        order.setTotalFee(total);
        // 1.5.其它属性
        order.setPaymentType(orderFormDTO.getPaymentType());
        order.setUserId(UserContext.getUser());
        order.setStatus(1);
        // 1.6.将Order写入数据库order表中
        save(order);

        // 2.保存订单详情
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        detailService.saveBatch(details);

        // 3.清理购物车商品
        cartApi.deleteCartItemByIds(itemIds);

        // 4.扣减库存
        try {
            List<OrderDetailReq> orderDetailReqs = BeanUtils.copyList(detailDTOS, OrderDetailReq.class);
            itemApi.deductStock(orderDetailReqs);
        } catch (Exception e) {
            throw new RuntimeException("库存不足！");
        }
        return order.getId();
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        order.setPayTime(LocalDateTime.now());
        updateById(order);
    }

    private List<OrderDetail> buildDetails(Long orderId, List<ItemResp> items, Map<Long, Integer> numMap) {
        List<OrderDetail> details = new ArrayList<>(items.size());
        for (ItemResp item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setName(item.getName());
            detail.setSpec(item.getSpec());
            detail.setPrice(item.getPrice());
            detail.setNum(numMap.get(item.getId()));
            detail.setItemId(item.getId());
            detail.setImage(item.getImage());
            detail.setOrderId(orderId);
            details.add(detail);
        }
        return details;
    }
}
