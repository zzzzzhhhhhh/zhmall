package com.heima.cart.controller;


import com.heima.cart.ICartService;
import com.heima.cart.api.api.CartApi;
import com.heima.cart.api.request.CartFormQo;
import com.heima.cart.api.request.CartQo;
import com.heima.cart.api.response.CartVO;
import com.heima.cart.dataobject.Cart;
import com.heima.common.utils.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "购物车相关接口")
@RestController
@RequiredArgsConstructor
public class CartController implements CartApi {
    private final ICartService cartService;

    public void addItem2Cart(CartFormQo cartFormDTO){
        cartService.addItem2Cart(cartFormDTO);
    }

    public void updateCart(@RequestBody CartQo cart){
        Cart cart1 = BeanUtils.copyBean(cart, Cart.class);
        cartService.updateById(cart1);
    }

    public void deleteCartItem(@Param ("购物车条目id")@PathVariable("id") Long id){
        cartService.removeById(id);
    }

    public List<CartVO> queryMyCarts(){
        return cartService.queryMyCarts();
    }
    @ApiOperation("批量删除购物车中商品")
    @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
    @DeleteMapping
    public void deleteCartItemByIds(@RequestParam("ids") List<Long> ids){
        cartService.removeByItemIds(ids);
    }
}
