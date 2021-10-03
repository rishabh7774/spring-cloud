package com.programming.rg.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSender {
    public void sendEmail(String orderNumber){
        log.info("Get The Request to Trigger Email for Order Id" + orderNumber);
        System.out.println("Order Placed Successfully with order Id "+ orderNumber);
        System.out.println("Email Sent");
        log.info("Triggered Email for Order Id" + orderNumber);
    }
}
