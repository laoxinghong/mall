package com.jitgur.mall.common.exception;

import com.jitgur.mall.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * Created by jitgur on 20230202
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult<Object> handle(ApiException e) {
        if (e.getCode() != null) {
            return CommonResult.failed(e.getCode());
        } else {
            return CommonResult.failed(e.getMessage());
        }
    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<String> handleValidityException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult<String> handleValidityException(BindException e) {
        BindingResult result = e.getBindingResult();
        String message = null;
        if (result.hasErrors()) {
            FieldError error = result.getFieldError();
            if (error != null) {
                message = error.getField() + error.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

}
