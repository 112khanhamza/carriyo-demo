package com.hamza.gofood.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private List<Item> items;
    private double amount;
}
