package com.heima.cart.client;

import com.heima.cart.api.dto.item.ItemDTO;
import com.heima.feign.component.ApiClient;
import com.heima.item.api.api.ItemApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Configuration
@Import(ApiClient.class)
public class ItemClient {


    @Bean
    public ItemApi itemApiConfig(ApiClient apiClient) throws Exception{
        return apiClient.buildClient(ItemApi.class);
    }

    /*@GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids);*/
}
