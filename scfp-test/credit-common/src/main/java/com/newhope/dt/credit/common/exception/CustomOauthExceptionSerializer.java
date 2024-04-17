package com.newhope.dt.phdb.common.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.newhope.dt.phdb.common.constant.HttpStatus;
import com.newhope.dt.phdb.common.domain.entity.AjaxResult;
import com.newhope.dt.phdb.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 自定义异常返回
 *
 * @author xts
 **/
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    public static final String BAD_CREDENTIALS = "Bad credentials";
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CustomOauthExceptionSerializer.class);

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(AjaxResult.CODE_TAG, HttpStatus.ERROR);
        if (StringUtils.equals(e.getMessage(), BAD_CREDENTIALS)) {
            jsonGenerator.writeStringField(AjaxResult.MSG_TAG, "用户名或密码错误");
        } else {
            log.warn("oauth2 认证异常 {} ", e);
            jsonGenerator.writeStringField(AjaxResult.MSG_TAG, e.getMessage());
        }
        jsonGenerator.writeEndObject();
    }
}