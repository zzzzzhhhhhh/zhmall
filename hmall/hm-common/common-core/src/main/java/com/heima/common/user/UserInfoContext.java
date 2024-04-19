package com.heima.common.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class UserInfoContext {

    public static final ThreadLocal<String> USERINFO = new ThreadLocal<>();

    public static void setUserinfo(String userId){
        USERINFO.set(userId);
    }

    public static String getUserInfo(){
        return USERINFO.get();
    }

    public static void clear() {
        USERINFO.remove();
    }
}
