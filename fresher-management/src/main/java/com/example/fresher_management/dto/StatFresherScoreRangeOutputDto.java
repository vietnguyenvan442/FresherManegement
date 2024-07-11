package com.example.fresher_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatFresherScoreRangeOutputDto {
    private String scoreRange;
    private long fresherCount;
}
