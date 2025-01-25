package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Birdy
 * @date 2025/1/25 15:49
 * @description Annonation
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = { StateValidation.class }
)  // 指定提供校验规则的类
public @interface State {
    // 提供校验失败后的提示信息
    String message() default "state 参数的值只能是已发布或者草稿";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
