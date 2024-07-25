package com.example.fresher_management.validate;

import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.User;

public interface UserValidate {
    public void validateMandatoryFields(User user);
    public void validateUniqueUsername(String username);
    public void validateUniqueCCCD(String cccd);
    public void validateUniqueEmail(String email);
}
