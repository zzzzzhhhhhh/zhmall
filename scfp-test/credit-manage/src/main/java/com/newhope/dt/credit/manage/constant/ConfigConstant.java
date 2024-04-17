package com.newhope.dt.phdb.common.constant;

/**
 * @description:
 * @author: xts
 * @create: 2021-08-28 15:56
 **/
public class ConfigConstant {
    public static final String[] GRANT_TYPE = {"password", "authorization_code", "refresh_token", "client_credentials"};
    public static final String SCOPE = "all";
    public static final String CLIENT_ID = "client";
    public static final String CLIENT_SECRET = "secret";
    public static final String RESOURCCEIDS = "resourceIds";
    // token过期时间配置
    public static final int TOKENVALIDITYSECONDS = 3600 * 24 * 7;
}
