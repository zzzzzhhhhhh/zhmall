package com.heima.pay;

import com.heima.feign.component.ApiClient;
import com.heima.user.api.UserApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiClient.class)
public class UserApiClientConfig {

    @Bean
    UserApi userApi(ApiClient apiClient) throws Exception{
        return apiClient.buildClient(UserApi.class);
    }

}
