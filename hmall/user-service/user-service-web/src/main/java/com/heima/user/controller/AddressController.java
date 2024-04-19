package com.heima.user.controller;


import com.heima.user.IAddressService;
import com.heima.user.dataobject.Address;
import com.heima.user.response.AddressVo;
import com.heima.common.exception.BadRequestException;
import com.heima.common.utils.BeanUtils;
import com.heima.common.utils.CollUtils;
import com.heima.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 虎哥
 */
@RestController
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;


    public AddressVo findAddressById(Long id) {
        // 1.根据id查询
        Address address = addressService.getById(id);
        // 2.判断当前用户
        Long userId = UserContext.getUser();
        if(!address.getUserId().equals(userId)){
            throw new BadRequestException("地址不属于当前登录用户");
        }
        return BeanUtils.copyBean(address, AddressVo.class);
    }

    public List<AddressVo> findMyAddresses() {
        // 1.查询列表
        List<Address> list = addressService.query().eq("user_id", UserContext.getUser()).list();
        // 2.判空
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        // 3.转vo
        return BeanUtils.copyList(list, AddressVo.class);
    }
}
