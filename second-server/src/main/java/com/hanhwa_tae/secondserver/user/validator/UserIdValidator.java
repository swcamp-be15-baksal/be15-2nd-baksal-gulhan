package com.hanhwa_tae.secondserver.user.validator;


import com.hanhwa_tae.secondserver.user.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UserIdValidator implements ConstraintValidator<ValidUserId, String> {
    private static final Pattern USER_ID_REGEX =
            Pattern.compile("^(?=.{5,20}$)[a-z0-9](?:[a-z0-9_-]*[a-z0-9])?$");


    @Override
    public boolean isValid(String userId, ConstraintValidatorContext constraintValidatorContext) {
        return !userId.isEmpty() && USER_ID_REGEX.matcher(userId).matches();
    }
}
