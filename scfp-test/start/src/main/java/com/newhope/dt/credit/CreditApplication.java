package com.newhope.dt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author sunzhongyu
 * @description
 * @date 2021/8/10 15:20
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.newhope.dt.credit.**", "com.newhope.dt.crpt.crptcommon.**"}, exclude = {
        SecurityAutoConfiguration.class})
@MapperScan("com.newhope.dt.phdb.**.mapper")
@EnableAsync
public class creditApplication {
}
