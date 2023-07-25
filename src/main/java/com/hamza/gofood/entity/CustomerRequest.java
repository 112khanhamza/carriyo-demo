package com.hamza.gofood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    List<String> items;
    boolean isPeakHour;
    boolean isSpecialDay;
    boolean isNight;
}
