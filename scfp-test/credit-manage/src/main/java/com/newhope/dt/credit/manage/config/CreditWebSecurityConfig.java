package security02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置登录页面
        http.formLogin()
                //可以修改登录的参数名
                .usernameParameter("my_username")
                .passwordParameter("my_password");

        // 授权配置
        //这里可以配置哪些url请求需要权限控制 下面代码是全部请求接口都需要权限控制 --------------------
        // 可以采用 http.authorizeRequests().antMatchers("/user/**").authenticated(); 这种方式， 设置哪些请求需要被权限控制
        // http.authorizeRequests().anyRequest().permitAll(); 这样可以放开权限控制， 使得满足要求的请求可以直接访问
        http.authorizeRequests().anyRequest().permitAll();
    }

}