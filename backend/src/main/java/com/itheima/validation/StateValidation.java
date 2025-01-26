package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Birdy
 * @date 2025/1/25 15:54
 * @description StateValidation
 */
public class StateValidation implements ConstraintValidator<State, String> {
    // <给哪个注解提供校验规则，校验数据类型>

    // 提供校验规则
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if("已发布".equals(s) || "草稿".equals(s)) {
            return true;
        }
        return false;
    }
}
