package com.heima.item.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.item.api.api.ItemApi;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.heima.item.dataobject.Item;
import com.heima.item.service.IItemService;
import com.heima.common.domain.PageDTO;
import com.heima.common.domain.PageQuery;
import com.heima.common.user.UserInfoContext;
import com.heima.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {

    private final IItemService itemService;


    public PageDTO<ItemResp> queryItemByPage(PageQuery query , String headerGateway,String userId) {


        log.info("itemService--------网关自定义header打印---------"+headerGateway);


        log.info("itemService--------网关获取登录用户id打印---------"+userId);

        String userId1 = UserInfoContext.getUserInfo();
        log.info("itemservice----------从userInfoContext中获取到的userId----------"+userId1);

        // 1.分页查询
        Page<Item> result = itemService.page(query.toMpPage("update_time", false));
        // 2.封装并返回
        return PageDTO.of(result, ItemResp.class);
    }


    public List<ItemResp> queryItemByIds(@RequestParam("ids") List<Long> ids){
        return itemService.queryItemByIds(ids);
    }


    public ItemResp queryItemById(@PathVariable("id") Long id) {
        return BeanUtils.copyBean(itemService.getById(id), ItemResp.class);
    }


    public void saveItem(@RequestBody ItemResp item) {
        // 新增
        itemService.save(BeanUtils.copyBean(item, Item.class));
    }


    public void updateItemStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status){
        Item item = new Item();
        item.setId(id);
        item.setStatus(status);
        itemService.updateById(item);
    }


    public void updateItem(@RequestBody ItemResp item) {
        // 不允许修改商品状态，所以强制设置为null，更新时，就会忽略该字段
        item.setStatus(null);
        // 更新
        itemService.updateById(BeanUtils.copyBean(item, Item.class));
    }


    public void deleteItemById(@PathVariable("id") Long id) {
        itemService.removeById(id);
    }


    public void deductStock(@RequestBody List<OrderDetailReq> items){
        itemService.deductStock(items);
    }
}
