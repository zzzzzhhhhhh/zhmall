package com.heima.feign.config;

import com.heima.common.user.UserInfoContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfoRequestInterceptor implements RequestInterceptor {

    public static final String AUTHORIZATION="Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION, UserInfoContext.getUserInfo());
    }
}
