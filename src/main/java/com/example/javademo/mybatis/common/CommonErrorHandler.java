package com.example.javademo.mybatis.common;
import com.example.javademo.mybatis.common.Exceptions.DuplicatedUser;
import com.example.javademo.mybatis.common.Exceptions.NotLogin;
import com.example.javademo.mybatis.common.Exceptions.NotUser;
import com.example.javademo.mybatis.common.Exceptions.PassWordError;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.common.Result.ReturnCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonErrorHandler {

    // @ExceptionHandler用来设置处理哪个异常
    @ExceptionHandler({PassWordError.class})
    public ResultData passwordException() {
        return ResultData.fail(ReturnCode.PassWordError.getCode(), ReturnCode.PassWordError.getMessage());
    }
    @ExceptionHandler({DuplicatedUser.class})
    public ResultData DuplicatedUser() {
        return ResultData.fail(ReturnCode.DuplicatedUser.getCode(), ReturnCode.DuplicatedUser.getMessage());
    }
    @ExceptionHandler({NotUser.class})
    public ResultData NotUser() {
        return ResultData.fail(ReturnCode.NotUser.getCode(), ReturnCode.NotUser.getMessage());
    }
    @ExceptionHandler({NotLogin.class})
    public ResultData NotLogin() {
        return ResultData.fail(ReturnCode.NotLogin.getCode(), ReturnCode.NotLogin.getMessage());
    }
    @ExceptionHandler({RuntimeException.class})
    public ResultData otherErrors(RuntimeException error) {
        System.out.println(error.toString());
        return ResultData.fail(ReturnCode.UNSUPPORTED_GRANT_TYPE.getCode(), error.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultData argumentExceptionHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        System.out.println("发生参数异常:{}" + message);
        return ResultData.fail(ReturnCode.ValidateParams.getCode(), message);
    }
}
