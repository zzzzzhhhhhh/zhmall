package com.heima.item.api.api;

import com.heima.item.api.ItemBaseApi;
import com.heima.item.api.request.OrderDetailReq;
import com.heima.item.api.response.ItemResp;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.PageQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ItemApi extends ItemBaseApi {

    @ApiOperation("分页查询商品")
    @GetMapping(ITEM_BASE_URL+"/page")
    PageDTO<ItemResp> queryItemByPage(PageQuery query);

    @ApiOperation("根据id批量查询商品")
    @GetMapping(ITEM_BASE_URL)
    List<ItemResp> queryItemByIds(@RequestParam("ids") List<Long> ids);

    @ApiOperation("根据id查询商品")
    @GetMapping(ITEM_BASE_URL+"{id}")
    ItemResp queryItemById(@PathVariable("id") Long id);

    @ApiOperation("新增商品")
    @PostMapping(ITEM_BASE_URL)
    void saveItem(@RequestBody ItemResp item);

    @ApiOperation("更新商品状态")
    @PutMapping(ITEM_BASE_URL+"/status/{id}/{status}")
    void updateItemStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);

    @ApiOperation("更新商品")
    @PutMapping(ITEM_BASE_URL)
    void updateItem(@RequestBody ItemResp item);

    @ApiOperation("根据id删除商品")
    @DeleteMapping(ITEM_BASE_URL+"{id}")
    void deleteItemById(@PathVariable("id") Long id);

    @ApiOperation("批量扣减库存")
    @PutMapping(ITEM_BASE_URL+"/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailReq> items);


}
