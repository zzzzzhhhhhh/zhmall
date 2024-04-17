package com.heima.cart;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.cart.api.request.CartFormQo;
import com.heima.cart.api.response.CartVO;
import com.heima.cart.dataobject.Cart;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface ICartService extends IService<Cart> {

    void addItem2Cart(CartFormQo cartFormDTO);

    List<CartVO> queryMyCarts();

    void removeByItemIds(Collection<Long> itemIds);
}
