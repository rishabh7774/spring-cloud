package com.programming.rg.inventoryservice.controller;

import com.programming.rg.inventoryservice.model.Inventory;
import com.programming.rg.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @GetMapping("check/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode) {
        log.info("Inventory Service Fetching Details for " + skuCode);
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot Find Product by Sku Code"  + skuCode));
        log.info("Successfully Fetched Details for " + skuCode);
        return inventory.getStock() > 0;
    }
}
