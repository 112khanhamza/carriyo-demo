package com.hamza.gofood.utils;

import com.hamza.gofood.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private static String[] itemNames = {"pizza", "burger", "coke", "brownies"};
    private static int[] amount = {150, 100, 50, 60};

    public static List<Item> getAvailableItems() {
        List<Item> availableItems = new ArrayList<>();
        for (int i = 0; i < itemNames.length; i++) {
            availableItems.add(new Item(itemNames[i], amount[i]));
        }
        return availableItems;
    }
}
