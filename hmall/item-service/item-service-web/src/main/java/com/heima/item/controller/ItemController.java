package com.heima.item.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.item.api.api.ItemApi;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.heima.item.dataobject.Item;
import com.heima.item.service.IItemService;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.PageQuery;
import com.hmall.common.utils.BeanUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理相关接口")
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {

    private final IItemService itemService;


    public PageDTO<ItemResp> queryItemByPage(PageQuery query) {
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
