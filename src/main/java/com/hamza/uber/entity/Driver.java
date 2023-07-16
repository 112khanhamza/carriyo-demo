package com.hamza.uber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
    // Prams -> driver, carModel, ratings, distanceFromCustomer
    private String driver;
    private String carModel;
    private double rating;
    private int distanceFromCustomer; // meters
    private List<String> preferredLocations;
}
