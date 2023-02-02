package com.jitgur.mall.common.exception;

import com.jitgur.mall.common.api.IErrorCode;

/**
 * 自定义API异常类
 * Created by jitgur on 20230202
 */
public class ApiException extends RuntimeException {

    private IErrorCode code;

    public ApiException(IErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public ApiException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IErrorCode getCode() {
        return code;
    }

}
