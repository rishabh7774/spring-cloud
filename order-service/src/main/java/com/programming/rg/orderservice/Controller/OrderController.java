package com.programming.rg.orderservice.Controller;

import com.programming.rg.orderservice.client.InventoryClient;
import com.programming.rg.orderservice.dto.OrderDto;
import com.programming.rg.orderservice.model.Order;
import com.programming.rg.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final StreamBridge streamBridge;


    @PostMapping("create")
    public String placeOrder(@RequestBody OrderDto orderDto) {

        log.info("Calling Inventory Service");
        boolean allProductsInStock = orderDto.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
        log.info("Inventory Service Work is Done");
        if (allProductsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());
            orderRepository.save(order);

            log.info("Sending it to Notification Service");
            streamBridge.send("notificationEventSupplier-out-0", order.getId());
            log.info("Order is Successfully Saved to Db");
            return "Order Placed Successfully";

        }
        return "Order Failed ";
    }

    @GetMapping("test")
    public String placeOrderTest() {
        return "Order Placed Successfully";
    }
}
