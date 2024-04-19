package com.heima.common.interceptor;

import com.heima.common.user.UserInfoContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * userInfo拦截器,用于拦截从网关转发来的请求,拦截后,从header中获取userId;
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION="Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(header)){
            UserInfoContext.setUserinfo(header);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //为了防止内存泄露,在使用完threadLocal后需要手动释放它
        UserInfoContext.clear();
    }
}
