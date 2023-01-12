package com.example.javademo.mybatis.common;

import com.example.javademo.mybatis.common.Errors.PassWordError;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.common.Result.ReturnCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonErrorHandler {

    // @ExceptionHandler用来设置处理哪个异常
    @ExceptionHandler({PassWordError.class})
    public ResultData passwordException() {
        return ResultData.fail(ReturnCode.PassWordError.getCode(), ReturnCode.PassWordError.getMessage());
    }
    @ExceptionHandler({RuntimeException.class})
    public ResultData otherErrors(RuntimeException error) {
        return ResultData.fail(ReturnCode.UNSUPPORTED_GRANT_TYPE.getCode(), error.getMessage());
    }
}
