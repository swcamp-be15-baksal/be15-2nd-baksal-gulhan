package com.hanhwa_tae.secondserver.user.validator;

import com.hanhwa_tae.secondserver.user.annotation.ValidBirth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class BirthValidator implements ConstraintValidator<ValidBirth, String> {

    private static final Pattern BIRTHDATE_PATTERN =
        Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");

    @Override
    public boolean isValid(String birth, ConstraintValidatorContext context) {
        if (birth == null || birth.isBlank()) return false;
        if(!isBeforeToday(birth)) return false;
        return BIRTHDATE_PATTERN.matcher(birth).matches();
    }

    private boolean isBeforeToday(String birth){
        return LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(LocalDate.now());
    }
}
