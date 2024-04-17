package com.newhope.dt.phdb.manager.admin.handler;

import com.alibaba.fastjson.JSON;
import com.newhope.dt.phdb.common.constant.HttpStatus;
import com.newhope.dt.phdb.common.domain.entity.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author xts
 * @createTime 2021/9/16
 * 当未登录或者token失效访问接口时，自定义的返回结果
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED);
        response.getWriter().println(JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED,"尚未登录，或者登录token已过期" )));
        response.getWriter().flush();
    }
}
