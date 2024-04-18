package com.heima.user.controller;

import com.heima.user.IUserService;
import com.heima.user.api.UserApi;
import com.heima.user.request.LoginFormQo;
import com.heima.user.response.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final IUserService userService;


    public UserLoginVO login(LoginFormQo loginFormDTO){
        return userService.login(loginFormDTO);
    }


    public void deductMoney(String pw,Integer amount){
        userService.deductMoney(pw, amount);
    }
}

