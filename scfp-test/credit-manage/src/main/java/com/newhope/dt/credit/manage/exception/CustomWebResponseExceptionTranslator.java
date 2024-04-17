package com.newhope.dt.phdb.manager.admin.exception;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.github.pagehelper.PageException;
import com.newhope.dt.phdb.common.enums.DaoExceptionDescEnum;
import com.newhope.dt.phdb.common.exception.CustomOauthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * OAuth2 自定义异常处理
 *
 * @author xts
 */
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        if (e instanceof InternalAuthenticationServiceException) {
            Throwable cause = e.getCause();
            if (cause instanceof MybatisPlusException || cause instanceof ApiException || cause instanceof PageException
                    || cause instanceof PersistenceException || cause instanceof DataAccessException) {
                log.error("Dao异常信息：{}", e.getMessage());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomOauthException(DaoExceptionDescEnum.Common_Dao_Exception.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomOauthException(e.getMessage()));
    }
}
