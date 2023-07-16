package com.hamza.uber;



import com.hamza.uber.entity.CustomerRequest;
import com.hamza.uber.entity.Driver;
import com.hamza.uber.entity.RideDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// extension: driver can cancel the request based on preferred destination provided by driver
public class Uber {

    private static final int COST_PER_KM = 8;
    private static final double MIN_RATING_OF_DRIVER = 4.0;

    public static void main(String[] args) {
        List<Driver> availableDrivers = new ArrayList<>();
        CustomerRequest customerRequest = new CustomerRequest();

        Driver driver = getDriver(availableDrivers, customerRequest);

        RideDetail rideDetail = getRideDetails(driver, customerRequest);
        System.out.println(rideDetail);
    }

    // Prams -> driver, carModel, ratings, distanceFromCustomer
    // step 1 : getAllModelsEligible
    // step 2 : check if the rating is >= 4.0
    // step 3 : get the least distance from customer

    public static RideDetail getRideDetails(Driver driver, CustomerRequest customerRequest) {
        RideDetail rideDetail = new RideDetail();
        rideDetail.setDriver(driver);
        rideDetail.setAmount(customerRequest.getDistance() * COST_PER_KM);
        return rideDetail;
    }

    public static Driver getDriver(List<Driver> availableDrivers, CustomerRequest customerRequest) {
        List<Driver> eligibleDrivers = filterDriversByCustomerRequest(availableDrivers, customerRequest);
        Driver nearestDriver = getNearestDriver(eligibleDrivers);

        return nearestDriver;
    }

    private static Driver getNearestDriver(List<Driver> eligibleDrivers) {
        int minDriverDistance = eligibleDrivers.get(0).getDistanceFromCustomer();
        Driver currentDriver = eligibleDrivers.get(0);

        for (int i = 1; i < eligibleDrivers.size(); i++) {
            int currentDriverDistance = eligibleDrivers.get(i).getDistanceFromCustomer();
            if (currentDriverDistance < minDriverDistance) {
                minDriverDistance = currentDriverDistance;
                currentDriver = eligibleDrivers.get(i);
            }
        }
        return currentDriver;
    }

    private static List<Driver> filterDriversByCustomerRequest(List<Driver> drivers, CustomerRequest customerRequest) {
        return drivers.stream()
                .filter(driver -> checkCustomerChoiceOfCar(driver, customerRequest))
                .filter(driver -> checkMinDriverRating(driver))
                .filter(driver -> checkPreferredDriverLocation(driver, customerRequest))
                .collect(Collectors.toList())
                ;
    }

    private static boolean checkPreferredDriverLocation(Driver driver, CustomerRequest customerRequest) {
        return driver.getPreferredLocations().contains(customerRequest.getLocation());
    }

    private static boolean checkMinDriverRating(Driver driver) {
        return driver.getRating() >= MIN_RATING_OF_DRIVER;
    }

    private static boolean checkCustomerChoiceOfCar(Driver driver, CustomerRequest customerRequest) {
        return driver.getCarModel().equals(customerRequest.getCarModel());
    }
}
