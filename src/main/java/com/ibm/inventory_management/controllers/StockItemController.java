package com.ibm.inventory_management.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ibm.inventory_management.models.StockItem;
import com.ibm.inventory_management.services.StockItemApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockItemController.class);

    private final StockItemApi service;

    public StockItemController(StockItemApi service) {
        this.service = service;
    }

    @GetMapping(path = "/stock-items", produces = "application/json")
    public List<StockItem> listStockItems() {
        List<StockItem> items = new ArrayList<StockItem>();
        try {
            items = this.service.listStockItems();
        } catch (Exception e) {
            LOGGER.error("Unable to get Stock Items Service", e);
        }
        return items;
    }
}
