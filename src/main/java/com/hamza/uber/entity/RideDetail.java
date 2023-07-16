package com.hamza.uber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RideDetail {
    private Driver driver;
    private double amount;

    public double getAmount() {
        return Math.ceil(amount);
    }
}
