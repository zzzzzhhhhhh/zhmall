package com.heima.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.heima.item.dataobject.Item;
import com.heima.item.dto.ItemDTO;
import com.heima.item.dto.OrderDetailDTO;


import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IItemService extends IService<Item> {

    void deductStock(List<OrderDetailReq> items);

    List<ItemResp> queryItemByIds(Collection<Long> ids);
}
