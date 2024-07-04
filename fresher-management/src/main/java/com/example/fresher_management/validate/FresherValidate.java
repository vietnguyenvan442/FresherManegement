package com.example.fresher_management.validate;

import com.example.fresher_management.entity.Fresher;

public interface FresherValidate {
    public void validateMandatoryFields(Fresher fresher);
    public void validateEmailFormat(String email);
    public void validatePhoneNumberFormat(String phoneNumber);
    public void validateUniqueUsername(String username);
    public void validateUniqueCCCD(String cccd);
    public void validateUniqueEmail(String email);
}
