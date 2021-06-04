package com.project.otholla.service;

import lombok.Data;

@Data
public class Item {
    private String sku;
    private String name;
    private String type;
    private String description;
    private String image_url;
    private Price price;
    private int remaining_uses;
    private int quantity;
    private String virtual_item_type;
}
