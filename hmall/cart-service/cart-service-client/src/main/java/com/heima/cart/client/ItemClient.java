package com.heima.cart.client;

import com.heima.feign.component.ApiClient;
import com.heima.item.api.api.ItemApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiClient.class)
public class ItemClient {


    @Bean
    public ItemApi itemApiConfig(ApiClient apiClient) throws Exception{
        return apiClient.buildClient(ItemApi.class);
    }
}
