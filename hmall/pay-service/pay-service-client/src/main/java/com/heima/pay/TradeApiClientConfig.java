package com.heima.pay;

import com.heima.feign.component.ApiClient;
import com.heima.trade.api.TradeApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiClient.class)
public class TradeApiClientConfig {

    @Bean
    TradeApi tradeApi(ApiClient apiClient) throws Exception{
        return apiClient.buildClient(TradeApi.class);
    }

}
