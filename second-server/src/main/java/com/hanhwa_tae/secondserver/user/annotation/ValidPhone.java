package com.hanhwa_tae.secondserver.user.annotation;

import com.hanhwa_tae.secondserver.user.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhone {

    String message() default "유효하지 않은 휴대폰 번호 양식입니다.";
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default {};
}
