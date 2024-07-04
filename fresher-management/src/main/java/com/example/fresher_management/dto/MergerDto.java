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
public class MergerDto {

    private int center_first_id;
    private int center_second_id;
    private Center center_new;
}
