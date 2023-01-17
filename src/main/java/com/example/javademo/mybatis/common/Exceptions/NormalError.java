package com.example.javademo.mybatis.common.Exceptions;

import com.example.javademo.mybatis.common.Result.ReturnCode;

public class NormalError extends RuntimeException {
    public NormalError() {
        super(ReturnCode.NORMAL_ERROR_ENUM.getMessage());
    }
}
