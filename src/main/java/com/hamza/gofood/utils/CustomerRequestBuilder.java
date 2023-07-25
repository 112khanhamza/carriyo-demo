package com.hamza.gofood.utils;

import com.hamza.gofood.entity.CustomerRequest;

import java.util.Arrays;
import java.util.Scanner;

public class CustomerRequestBuilder {

    public static CustomerRequest createCustomerRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Items: ");
        String[] items = scanner.nextLine()
                .replace(" ", "")
                .split(",");

        System.out.print("SpecialDay: ");
        boolean specialDay = scanner.next().equalsIgnoreCase("Yes") ? true : false;

        System.out.print("Peak hour: ");
        boolean peakHour = scanner.next().equalsIgnoreCase("Yes") ? true : false;

        System.out.print("Night order: ");
        boolean nightOrder = scanner.next().equalsIgnoreCase("Yes") ? true : false;

        return new CustomerRequest(Arrays.asList(items), peakHour, specialDay, nightOrder);
    }
}
