package com.sa.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private String restaurantName;
    private String userEmail;
}
