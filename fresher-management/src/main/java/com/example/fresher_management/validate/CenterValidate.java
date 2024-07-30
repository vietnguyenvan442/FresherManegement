package com.example.fresher_management.validate;

import com.example.fresher_management.entity.Center;

public interface CenterValidate {
    public void validateMandatoryFields(Center center);

    public void validateUniqueEmail(String email);

    public void validateUniquePhongNumber(String sdt);
}
