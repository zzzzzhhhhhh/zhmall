package com.heima.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.heima.trade.mapper")
@SpringBootApplication(scanBasePackages = "com.heima.*")
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class,args);
    }
}