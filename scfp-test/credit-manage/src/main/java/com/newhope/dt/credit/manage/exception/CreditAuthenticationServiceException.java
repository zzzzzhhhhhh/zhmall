package com.newhope.dt.crpt.service.excption;

import com.newhope.dt.crpt.common.enums.BaseEnum;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/***
 * @author LCM
 * @date 2023/3/19 17:44
 */
public class ScfpAuthenticationServiceException extends InternalAuthenticationServiceException {

    private String code;

    public ScfpAuthenticationServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ScfpAuthenticationServiceException(BaseEnum baseEnum) {
        super(baseEnum.getErrMessage());
        this.code = baseEnum.getErrCode();
    }

    public static ScfpAuthenticationServiceException buildCustomerException(BaseEnum baseEnum) {
        return new ScfpAuthenticationServiceException(baseEnum);
    }


    public String getCode() {
        return code;
    }
}
