package com.newhope.dt.phdb.common.enums;

import com.newhope.dt.crpt.crptcommon.exception.enums.CustomizeExceptionDesc;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: limao
 * @Date: 2021/10/8 19:14
 **/

@AllArgsConstructor
@Getter
public enum DaoExceptionDescEnum {


    Common_Dao_Exception("通用dao异常", "commonDaoException", "数据库异常");

    /**
     * 业务场景
     */
    private String bizScene;

    /**
     * 自定义描述
     */
    private String customize;

    /**
     * 给前端的提示message
     */
    private String message;

    public CustomizeExceptionDesc toObject() {
        return CustomizeExceptionDesc.builder().bizScene(this.bizScene).customize(this.customize).message(this.message)
                .build();
    }
}
