package com.example.javademo.mybatis.common.Validators.CustomValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CaseUpperValidator implements ConstraintValidator<CaseUpper, String> {

    public String message;

    @Override
    public void initialize(CaseUpper caseUpper) {
        this.message = caseUpper.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 为空，返回失败
        boolean isValid = false;
        System.out.println("value" + value);
        if (value == null) {
            // 如果空指针可以直接不验证
            // 抛出javax.validation.ValidationException
            return true;
        }
        // 都是大写校验通过
        if (value.equals(value.toUpperCase())) {
            isValid = true;
        }
        // 校验不通过，实现自定义错误信息
        if (!isValid) {
            //禁止默认消息返回
            context.disableDefaultConstraintViolation();
            //自定义返回消息
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return isValid;
    }
}
