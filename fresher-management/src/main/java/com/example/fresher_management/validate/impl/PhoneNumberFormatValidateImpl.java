package com.example.fresher_management.validate.impl;

import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneNumberFormatValidateImpl implements PhoneNumberFormatValidate {
    private static final String PHONE_REGEX = "^\\d{10}$";
    @Override
    public void validatePhoneNumberFormat(String phoneNumber) {
        if (!Pattern.matches(PHONE_REGEX, phoneNumber)) {
            throw new ValidationException("Invalid phone number format");
        }
    }
}
