package com.heima.cart.api.api;

import com.heima.cart.api.CartBaseApi;
import com.heima.cart.api.request.CartFormQo;
import com.heima.cart.api.request.CartQo;
import com.heima.cart.api.response.CartVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CartApi extends CartBaseApi {

    String CART_BASE_URL="/carts";

    @PostMapping(CART_BASE_URL)
    void addItem2Cart(@Valid @RequestBody CartFormQo cartFormDTO);

    @PutMapping(CART_BASE_URL)
    void updateCart(@RequestBody CartQo cart);

    @DeleteMapping(CART_BASE_URL+"{id}")
    void deleteCartItem( @PathVariable("id") Long id);

    @GetMapping(CART_BASE_URL)
    List<CartVO> queryMyCarts();

    @DeleteMapping(CART_BASE_URL)
    void deleteCartItemByIds(@RequestParam("ids") List<Long> ids);
}
