package com.heima.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.user.dataobject.User;
import com.heima.user.request.LoginFormQo;
import com.heima.user.response.UserLoginVO;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormQo loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
