package com.ibm.inventory_management.services;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.ibm.inventory_management.config.CloudantConfig;
import com.ibm.inventory_management.models.StockItem;

@Service
@Primary
public class StockItemService implements StockItemApi {
    private CloudantConfig config;
    private CloudantClient client;
    private Database db = null;

    public StockItemService(CloudantConfig config, CloudantClient client) {
        this.config = config;
        this.client = client;
    }

    @PostConstruct
    public void init() {
        db = client.database(config.getDatabaseName(), true);
    }

    @Override
    public List<StockItem> listStockItems() throws Exception {

        try {
            return db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(StockItem.class);

        } catch (IOException e) {
            throw new Exception("", e);
        }
    }
}

/*
 * 
 * @Service public class StockItemService implements StockItemApi {
 * 
 * @Override public List<StockItem> listStockItems() { return asList(new
 * StockItem("1").withName("Item 1").withStock(100).withPrice(10.5).
 * withManufacturer("Sony"), new
 * StockItem("2").withName("Item 2").withStock(150).withPrice(100.0).
 * withManufacturer("Insignia"), new
 * StockItem("3").withName("Item 3").withStock(10).withPrice(1000.0).
 * withManufacturer("Panasonic"), new
 * StockItem("4").withName("Item 4").withStock(10).withPrice(1000.0).
 * withManufacturer("Apple"), new
 * StockItem("5").withName("Item 5").withStock(10).withPrice(1000.0)
 * .withManufacturer("From SVC Backend - Microsoft")); } }
 * 
 */
