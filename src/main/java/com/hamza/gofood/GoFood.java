package com.hamza.gofood;

import com.hamza.gofood.entity.Bill;
import com.hamza.gofood.entity.CustomerRequest;
import com.hamza.gofood.entity.Item;
import com.hamza.gofood.entity.Rule;
import com.hamza.gofood.utils.CustomerRequestBuilder;
import com.hamza.gofood.utils.ItemBuilder;
import com.hamza.gofood.utils.RuleBuilder;

import java.util.ArrayList;
import java.util.List;

public class GoFood {

    private final float GST_PERCENTAGE = 5;
    private final double NORMAL_DELIVERY_CHARGE = 20;
    private final List<Item> AVAILABLE_ITEMS = ItemBuilder.getAvailableItems();
    private final List<Rule> AVAILABLE_RULES = RuleBuilder.getAvailableRules();

    public static void main(String[] args) {
        CustomerRequest customerRequest = CustomerRequestBuilder.createCustomerRequest();

        GoFood goFood = new GoFood();
        Bill bill = goFood.getBill(customerRequest);
        System.out.println(bill);
    }

    public Bill getBill(CustomerRequest customerRequest) {
        Bill bill = new Bill();
        if (customerRequest.getItems() == null) return bill;

        List<String> itemsRequested = customerRequest.getItems();
        List<Item> finalItems = checkItemsInInventory(itemsRequested);
        List<Rule> rules = checkRulesToApply(customerRequest);

        double totalOrderAmount = getTotalItemAmount(finalItems);
        double deliveryCharge = getDeliveryCharge(rules);
        double gstAmount = getGstAmount(totalOrderAmount);
        bill.setItems(finalItems);
        bill.setAmount(totalOrderAmount+deliveryCharge+gstAmount);
        return bill;
    }

    private List<Rule> checkRulesToApply(CustomerRequest customerRequest) {
        List<Rule> finalRules = new ArrayList<>();
        List<String> customerRules = new ArrayList<>();
        if (customerRequest.isPeakHour()) customerRules.add("peak hour");
        if (customerRequest.isNight()) customerRules.add("night charges");
        if (customerRequest.isSpecialDay()) customerRules.add("special days");

        for (int i = 0; i < customerRules.size(); i++) {
            String ruleStr = customerRules.get(i);
            Rule rule = AVAILABLE_RULES.stream()
                    .filter(rul -> rul.getRuleName().equals(ruleStr))
                    .findFirst().orElse(null);
            if (rule != null) finalRules.add(rule);
        }
        return finalRules;
    }

    private List<Item> checkItemsInInventory(List<String> itemsRequested) {
        List<Item> finalItems = new ArrayList<>();
        for (int i = 0; i < itemsRequested.size(); i++) {
            String itemStr = itemsRequested.get(i);
            Item item = AVAILABLE_ITEMS.stream()
                    .filter(itm -> itm.getItemName().equals(itemStr))
                    .findFirst().orElse(null);

            if (item != null) finalItems.add(item);
        }
        return finalItems;
    }

    private double getGstAmount(double amount) {
        return (amount * GST_PERCENTAGE) / 100;
    }

    private Double getTotalItemAmount(List<Item> items) {
        return items.stream().mapToDouble(Item::getAmount).sum();
    }

    private Double getDeliveryCharge(List<Rule> rules) {
        if (rules == null) return NORMAL_DELIVERY_CHARGE;
        return rules.stream().mapToDouble(Rule::getAmount).sum();
    }
}
