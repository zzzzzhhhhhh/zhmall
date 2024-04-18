package com.heima.user.api;

import com.heima.user.UserBaseApi;
import com.heima.user.response.AddressVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AddressApi extends UserBaseApi {

    String ADDRESS_BASE_URL="/addresses";

    @GetMapping(ADDRESS_BASE_URL+"{addressId}")
    AddressVo findAddressById(@ApiParam("地址id") @PathVariable("addressId") Long id);

    @GetMapping(ADDRESS_BASE_URL)
    List<AddressVo> findMyAddresses();
}
