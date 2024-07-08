package com.example.fresher_management.validate.impl;

import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.validate.EmailFormatValidate;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailFormatValidateImpl implements EmailFormatValidate {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public void validateEmailFormat(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new ValidationException("Invalid email format");
        }
    }
}
