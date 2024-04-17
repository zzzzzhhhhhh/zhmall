package com.newhope.dt.crpt.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * @author LCM
 * @date 2023/3/19 17:14
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionDescEnum implements BaseEnum {


    NOT_SUPPORT("4000", "认证方式未实现，请联系管理人员"),
    USER_NOT_EXIST("4001", "用户信息不存在，请先注册"),
    NOT_RELATION("4002", "用户未注册，请先完成注册"),
    CALL_FLY_BOOK_ERROR("4003", "飞书登陆失败，请重试"),
    MISMATCH("4004", "用户名或密码错误，请重试"),
    SYSTEM_ERROR("4005", "系统异常，请重试"),
    EXPIRED_OR_INVALID("4006", "认证已过期或无效"),



    OVERED_FAIL_COUNT("4007", "对不起，您的账号【%s】已被锁定，请联系管理员"),

    ACCOUNT_FORBIDDEN("4008","当前账号已停用，请联系管理员"),
    NOT_RELATION_FLY("4009", "当前账号未绑定飞书，请先使用飞书扫码登陆"),
    GET_APP_ACCESS_TOKEN_ERROR("4010", "飞书免密登录获取APP_TOKEN失败"),
    GET_USER_ACCESS_TOKEN_ERROR("4011", "飞书免密登录获取USER_TOKEN失败"),
    LARK_NO_PASS_LOGIN_ERROR("4012", "飞书免密登录失败"),
    CODE_NOT_NULL("4013", "飞书免密登录CODE不能为空"),
    LOGIN_RELATION_FAIL("4020", "登陆关联系统失败，请新登录"),
    LARK_PHONE_EMPTY("4021", "未获取到您的飞书手机号，请重试"),
    ALREADY_BIDING("4022", "您已完成供应链金融平台账号与飞书绑定，无需重复绑定"),
    ;

    private String errCode;
    private String errMessage;
}
