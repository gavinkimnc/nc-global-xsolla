package com.project.otholla.controller;

import com.project.otholla.service.Item;
import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private List<Item> items;

}
