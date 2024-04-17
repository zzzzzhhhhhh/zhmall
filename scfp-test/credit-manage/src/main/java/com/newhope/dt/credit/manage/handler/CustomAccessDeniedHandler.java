package com.newhope.dt.phdb.manager.admin.handler;

import com.alibaba.fastjson.JSON;
import com.newhope.dt.crpt.crptcommon.core.api.ApiResult;
import com.newhope.dt.crpt.crptcommon.core.api.error.ErrorCode;
import com.newhope.dt.phdb.common.constant.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义访问无权限资源时的异常
 * 
 * @author xts
 */
@Component
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler
{
    private final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
        logger.info("权限不足，请联系管理员 {}", request.getRequestURI());

        String msg = authException.getMessage();
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(ApiResult.buildFailure(new ErrorCode(),"权限不足，请联系管理员" + msg)));
        response.getWriter().flush();
    }
}
