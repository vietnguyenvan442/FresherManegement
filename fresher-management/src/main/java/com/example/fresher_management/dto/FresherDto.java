package com.example.fresher_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FresherDto {
    private String username;
    private String password;
    private String name;
    private String dob;
    private String cccd;
    private String sdt;
    private String address;
    private String email;
    private float salary;
    private boolean state;
    private int languageId;
}
