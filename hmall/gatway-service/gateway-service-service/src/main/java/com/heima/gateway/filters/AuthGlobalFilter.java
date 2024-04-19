package com.heima.gateway.filters;

import com.heima.gateway.config.AuthProperties;
import com.heima.gateway.exception.UnauthorizedException;
import com.heima.gateway.utils.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthGlobalFilter implements GlobalFilter , Ordered {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private JwtTool jwtTool;

    public static final String AUTHORIZATION = "Authorization";

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取请求凭证
        String path = exchange.getRequest().getPath().pathWithinApplication().value();

        //判断是否直接放行
        if (isExcludePath(path)){
            return chain.filter(exchange);
        }
        //获取token,校验解析
        Long userId = null;
        try {
            List<String> authorization = exchange.getRequest().getHeaders().get(AUTHORIZATION);
            if (CollectionUtils.isEmpty(authorization)){
                throw new UnauthorizedException("用户未登录");
            }
            String token = authorization.get(0);
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //过滤链终止,不再继续请求服务
            return response.setComplete();
        }

        //传递用户信息给其他微服务
        String userIdString = userId.toString();
        ServerWebExchange serverWebExchange = exchange.mutate().request(builder -> builder.header(AUTHORIZATION, userIdString)).build();

        return chain.filter(serverWebExchange);
    }

    /**
     * 判断是否是排除的路径
     * @param path
     * @return
     */
    private boolean isExcludePath(String path) {
        List<String> excludePaths = authProperties.getExcludePaths();
        for (String excludePath : excludePaths) {
            if (antPathMatcher.match(excludePath,path)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
