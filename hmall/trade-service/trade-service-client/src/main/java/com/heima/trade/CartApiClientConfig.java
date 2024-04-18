package com.heima.trade;

import com.heima.cart.api.api.CartApi;
import com.heima.feign.component.ApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiClient.class)
public class CartApiClientConfig {

    @Bean
    CartApi cartApi(ApiClient apiClient) throws Exception{
        return apiClient.buildClient(CartApi.class);
    }

}
