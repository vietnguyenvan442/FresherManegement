package com.example.fresher_management.dto;

import com.example.fresher_management.entity.Center;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatCenterOutputDto {
    private Center center;
    private long total_fresher;
}