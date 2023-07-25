package com.hamza.uber;



import com.hamza.uber.entity.CustomerRequest;
import com.hamza.uber.entity.Driver;
import com.hamza.uber.entity.RideDetail;
import com.hamza.uber.utils.DriverBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// extension: driver can cancel the request based on preferred destination provided by driver
public class Uber {

    private static final int COST_PER_KM = 8;
    private static final double MIN_RATING_OF_DRIVER = 4.0;

    public static void main(String[] args) {
        // Create Customer Request
        CustomerRequest customerRequest = new CustomerRequest("sedan", 100, "mumbai");

        // Generate Available Drivers
        List<Driver> availableDrivers = DriverBuilder.generateDrivers();

        Uber uber = new Uber();
        Driver driver = uber.getDriver(availableDrivers, customerRequest);
        RideDetail rideDetail = uber.getRideDetails(driver, customerRequest);
        System.out.println(rideDetail);
    }

    // Prams -> driver, carModel, ratings, distanceFromCustomer
    // step 1 : getAllModelsEligible
    // step 2 : check if the rating is >= 4.0
    // step 3 : get the least distance from customer

    public RideDetail getRideDetails(Driver driver, CustomerRequest customerRequest) {
        RideDetail rideDetail = new RideDetail();
        if (driver == null) return rideDetail;

        rideDetail.setDriver(driver);
        rideDetail.setAmount(customerRequest.getDistance() * COST_PER_KM);
        return rideDetail;
    }

    public Driver getDriver(List<Driver> availableDrivers, CustomerRequest customerRequest) {
        List<Driver> eligibleDriversByCustomerRequest = filterDriversByCustomerRequest(availableDrivers, customerRequest);
        Driver nearestDriver = filterDrivers(eligibleDriversByCustomerRequest);

        return nearestDriver;
    }

    private Driver filterDrivers(List<Driver> drivers) {
        return drivers.stream()
                .filter(driver -> checkMinDriverRating(driver))
                .sorted(Comparator.comparing(Driver::getRating).reversed())
                .min(Comparator.comparing(Driver::getDistanceFromCustomerInMeters))
                .orElse(null)
                ;
    }

    private List<Driver> filterDriversByCustomerRequest(List<Driver> drivers, CustomerRequest customerRequest) {
        return drivers.stream()
                .filter(driver -> checkCustomerChoiceOfCar(driver, customerRequest))
                .filter(driver -> checkPreferredDriverLocation(driver, customerRequest))
                .collect(Collectors.toList())
                ;
    }

    private boolean checkPreferredDriverLocation(Driver driver, CustomerRequest customerRequest) {
        return driver.getPreferredLocations().contains(customerRequest.getLocation());
    }

    private boolean checkMinDriverRating(Driver driver) {
        return driver.getRating() >= MIN_RATING_OF_DRIVER;
    }

    private boolean checkCustomerChoiceOfCar(Driver driver, CustomerRequest customerRequest) {
        return driver.getCarModel().equals(customerRequest.getCarModel());
    }
}