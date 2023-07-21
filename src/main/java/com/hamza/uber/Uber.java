package com.hamza.uber;



import com.hamza.uber.entity.CustomerRequest;
import com.hamza.uber.entity.Driver;
import com.hamza.uber.entity.RideDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// extension: driver can cancel the request based on preferred destination provided by driver
public class Uber {

    private static final int COST_PER_KM = 8;
    private static final double MIN_RATING_OF_DRIVER = 4.0;

    public static void main(String[] args) {
        // Create Customer Request
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCarModel("sedan");
        customerRequest.setDistance(100);
        customerRequest.setLocation("mumbai");

        // Generate Available Drivers
        List<Driver> availableDrivers = new ArrayList<>();
        String[] drivers = {"A", "B", "C", "D"};
        String[] carModels = {"sedan", "hatchback", "sedan", "suv"};
        double[] ratings = {4.3, 4.3, 4.0, 4.5};
        int[] distanceFromCustomer = {100, 250, 100, 300};
        String[][] preferredLocations = {{"delhi","hyderabad", "delhi"},
                {"mumbai","hyderabad", "delhi"},
                {"mumbai","hyderabad", "delhi"},
                {"mumbai","hyderabad", "delhi"}};
        for (int i = 0; i < 4; i++) {
            Driver driver = new Driver(drivers[i], carModels[i], ratings[i],
                    distanceFromCustomer[i], Arrays.asList(preferredLocations[i]));
            availableDrivers.add(driver);
        }

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
        List<Driver> eligibleDrivers = filterDriversByCustomerRequest(availableDrivers, customerRequest);
        Driver nearestDriver = getNearestDriver(eligibleDrivers);

        return nearestDriver;
    }

    private Driver getNearestDriver(List<Driver> eligibleDrivers) {
        if (eligibleDrivers.size() == 0) return null;

        int minDriverDistance = eligibleDrivers.get(0).getDistanceFromCustomerInMeters();
        Driver currentDriver = eligibleDrivers.get(0);

        for (int i = 1; i < eligibleDrivers.size(); i++) {
            int currentDriverDistance = eligibleDrivers.get(i).getDistanceFromCustomerInMeters();
            if (currentDriverDistance < minDriverDistance) {
                minDriverDistance = currentDriverDistance;
                currentDriver = eligibleDrivers.get(i);
            }
        }
        return currentDriver;
    }

    private List<Driver> filterDriversByCustomerRequest(List<Driver> drivers, CustomerRequest customerRequest) {
        return drivers.stream()
                .filter(driver -> checkCustomerChoiceOfCar(driver, customerRequest))
                .filter(driver -> checkMinDriverRating(driver))
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