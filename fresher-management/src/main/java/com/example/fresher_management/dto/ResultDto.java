package com.example.fresher_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private float point;
    private int test_id;
    private int fresher_id;
    private String start_test;
    private String end_test;
}
