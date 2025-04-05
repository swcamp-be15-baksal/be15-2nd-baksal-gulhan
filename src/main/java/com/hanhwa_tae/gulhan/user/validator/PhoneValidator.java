package com.hanhwa_tae.gulhan.user.validator;

import com.hanhwa_tae.gulhan.user.annotation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    private static final Pattern PHONE_REGEX =
            Pattern.compile("^01[0|1|6|7|8|9]-\\d{3,4}-\\d{4}$");

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && PHONE_REGEX.matcher(phone).matches();
    }
}