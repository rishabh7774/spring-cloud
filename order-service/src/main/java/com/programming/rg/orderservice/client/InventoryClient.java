package com.programming.rg.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {
    @GetMapping("/inventory/check/{skuCode}")
    Boolean checkStock(@PathVariable String skuCode);
}
