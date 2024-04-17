package com.heima.cart.api.api;

import com.heima.cart.api.request.CartFormQo;
import com.heima.cart.api.request.CartQo;
import com.heima.cart.api.response.CartVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CartApi {

    @ApiOperation("添加商品到购物车")
    @PostMapping
    void addItem2Cart(@Valid @RequestBody CartFormQo cartFormDTO);

    @ApiOperation("更新购物车数据")
    @PutMapping
    void updateCart(@RequestBody CartQo cart);

    @ApiOperation("删除购物车中商品")
    @DeleteMapping("{id}")
    void deleteCartItem( @PathVariable("id") Long id);

    @ApiOperation("查询购物车列表")
    @GetMapping
    List<CartVO> queryMyCarts();

    @ApiOperation("批量删除购物车中商品")
    @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
    @DeleteMapping
    void deleteCartItemByIds(@RequestParam("ids") List<Long> ids);
}
