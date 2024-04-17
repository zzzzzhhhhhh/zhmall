package com.newhope.dt.phdb.manager.admin.config;

import com.alibaba.fastjson.JSON;
import com.newhope.dt.phdb.common.config.PermitAllUrlProperties;
import com.newhope.dt.phdb.common.constant.ConfigConstant;
import com.newhope.dt.phdb.manager.admin.handler.CustomAccessDeniedHandler;
import com.newhope.dt.phdb.manager.admin.handler.RestAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author XTS-xiong
 * @Description 资源服务器用来验证token
 * @Date 2021/9/10 13:17
 */
@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    /**
     * 无权限拦截器
     */
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ConfigConstant.RESOURCCEIDS) // 配置资源id，这里的资源id和授权服务器中的资源id一致
                .stateless(true); // 设置这些资源仅基于令牌认证
        //自定义资源访问认证异常，没有token，或token错误，使用MyAuthenticationEntryPoint
        resources.authenticationEntryPoint(new RestAuthenticationEntryPoint());
        resources.accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    // 配置 URL 访问权限
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

        String[] urls = permitAllUrlProperties.getUrls().stream().distinct().toArray(String[]::new);
        log.info("\n ============放行路径{}", JSON.toJSONString(urls));
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        registry.antMatchers(urls).permitAll();
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }

}
