package com.newhope.dt.crpt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author voldemort
 */
@AllArgsConstructor
@Getter
public enum AdminUserStatusEnum {

    NORMAL_ACCOUNT("0","正常"),

    FORBIDDEN_ACCOUNT("1","停用");

    private String type;

    private String value;
}
