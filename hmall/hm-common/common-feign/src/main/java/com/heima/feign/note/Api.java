package com.heima.feign.note;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Api {

    /**
     * 指定服务名称
     */
    String serviceName();

}
