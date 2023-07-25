package com.hamza.uber.utils;

import com.hamza.uber.entity.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverBuilder {

    private static String[] drivers = {"A", "B", "C", "D"};
    private static String[] carModels = {"sedan", "hatchback", "sedan", "suv"};
    private static double[] ratings = {4.0, 4.3, 4.3, 4.5};
    private static int[] distanceFromCustomer = {100, 250, 100, 300};
    private static String[][] preferredLocations =
            {{"mumbai","hyderabad", "delhi"},
            {"mumbai","hyderabad", "delhi"},
            {"mumbai","hyderabad", "delhi"},
            {"mumbai","hyderabad", "delhi"}};

    public static List<Driver> generateDrivers() {
        List<Driver> randomDrivers = new ArrayList<>();
        for (int i = 0; i < drivers.length; i++) {
            Driver driver = new Driver(drivers[i], carModels[i], ratings[i],
                    distanceFromCustomer[i], Arrays.asList(preferredLocations[i]));
            randomDrivers.add(driver);
        }
        return randomDrivers;
    }
}
