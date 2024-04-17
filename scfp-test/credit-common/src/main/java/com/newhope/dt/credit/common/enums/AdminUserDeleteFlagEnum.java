package com.newhope.dt.crpt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author voldemort
 */
@AllArgsConstructor
@Getter
public enum AdminUserDeleteFlagEnum {

    EXIST("0","存在"),

    DELETED("2","删除");

    private String type;

    private String value;
}
