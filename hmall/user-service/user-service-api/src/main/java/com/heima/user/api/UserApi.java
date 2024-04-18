package com.heima.user.api;

import com.heima.user.UserBaseApi;
import com.heima.user.request.LoginFormQo;
import com.heima.user.response.UserLoginVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi extends UserBaseApi{

    String USER_BASE_URL="/users";

    @PostMapping(USER_BASE_URL+"/login")
    UserLoginVO login(@RequestBody @Validated LoginFormQo loginFormDTO);

    @PutMapping(USER_BASE_URL+"/money/deduct")
    void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);

}
