package com.example.javademo.mybatis.common.Result;

public enum ReturnCode {
    NotLogin(401, "登陆过期"),
    RC100(0, "操作成功"),
    PassWordError(1, "密码错误"),
    DuplicatedUser(2, "用户已注册"),
    ValidateParams(3, "参数校验失败"),
    NORMAL_ERROR_ENUM(5, "不支持的认证模式"),
    NotUser(6, "用户不存在"),
    UNSUPPORTED_GRANT_TYPE(7, "不支持的认证模式");
    private final int code;
    /**
     * 自定义描述
     **/
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
