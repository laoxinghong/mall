package com.jitgur.mall.common.exception;

import com.jitgur.mall.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by jitgur on 20230202
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode code) {
        throw new ApiException(code);
    }

}
