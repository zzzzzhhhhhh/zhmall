package com.newhope.dt.phdb.manager.admin.config;

import cn.hutool.core.util.StrUtil;
import com.newhope.dt.phdb.common.constant.SecurityConstants;
import com.newhope.dt.phdb.common.domain.entity.LoginUser;
import com.newhope.dt.phdb.common.enums.RedisKeyPathEnum;
import com.newhope.dt.phdb.manager.admin.config.service.CustomAuthenticationKeyGenerator;
import com.newhope.dt.phdb.manager.admin.config.service.SingleTokenServices;
import com.newhope.dt.phdb.manager.admin.exception.CustomWebResponseExceptionTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: xiongtangshuai
 * @Description: OAuth2 认证服务配置
 * @Date Create in 2021/9/9 20:15
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager; // 该对象用来支持 password 模式

    @Autowired
    UserDetailsService userDetailsService;     // 该对象将为刷新token提供支持

    @Autowired
    private DataSource dataSource;  //数据库资源

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        //InMemoryTokenStore  tokenStore=new InMemoryTokenStore();(基于内存存储)
        //JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource); //基于数据库存储
        //RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory); //基于redis
        // 指定redis数据库存储token，与业务库区分。
        //LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        //lettuceConnectionFactory.setDatabase(16);
        //MyRedisTokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory);
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis token存储中的前缀
        tokenStore.setPrefix(RedisKeyPathEnum.Phdb_Oauth.getKey() +"auth-token:");
        tokenStore.setAuthenticationKeyGenerator(new CustomAuthenticationKeyGenerator()); //自定义access_token生成规则
        return tokenStore;
    }

    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            // 请求方式
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
            //指定token配置令牌的存储（这里存放在数据库中）
            .tokenStore(tokenStore())
            // 自定义生成令牌给前端
            .tokenEnhancer(tokenEnhancer())
            //挤下线自定义规则
            .tokenServices(tokenServices(endpoints))
            //指定认证管理器
            .authenticationManager(authenticationManager)
            // 用户账号密码认证
            .userDetailsService(userDetailsService)
            // 是否重复使用 refresh_token
            .reuseRefreshTokens(false)
            // 自定义异常处理
            .exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    // 配置客户端详情基于数据库
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //这个地方指的是从jdbc查出数据来存储
        clients.withClientDetails(clientDetails());
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    /**
     * 自定义生成令牌
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (authentication.getUserAuthentication() != null) {
                Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
                LoginUser user = (LoginUser)authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put("client_id", authentication.getOAuth2Request().getClientId());
                additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
                //自定义令牌返回信息
                // 此处只是返回给前端显示的token去掉了_userId，目的：供应链金融平台统一token
                additionalInformation.put("code", 200);//返回成功
                additionalInformation.put("data", accessToken.getValue());
                additionalInformation.put("msg", accessToken.getValue());
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }



    /**
     * 只允许一个登录账号在线，后登录的将挤下前登录的自定义
     *
     * @param endpoints
     * @return
     */
    private SingleTokenServices tokenServices(AuthorizationServerEndpointsConfigurer endpoints) {
        SingleTokenServices tokenServices = new SingleTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);//支持刷新token
        tokenServices.setReuseRefreshToken(false);// 不复用refreshToken 每次刷新token之后返回一个新的
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        addUserDetailsService(tokenServices, this.userDetailsService);
        return tokenServices;
    }

    private void addUserDetailsService(SingleTokenServices tokenServices, UserDetailsService userDetailsService) {
        if (userDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
            tokenServices.setAuthenticationManager(new ProviderManager(Arrays.asList(provider)));
        }
    }
}
