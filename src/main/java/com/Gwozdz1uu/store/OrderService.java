package com.Gwozdz1uu.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

//@Service
public class OrderService {
    private PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService){
        this.paymentService=paymentService;
        System.out.println("Order service is created");
    }

    @PostConstruct
    public void init(){
        System.out.println("Order Service PostConstruct");
    }
    @PreDestroy
    public void cleanup(){
        System.out.println("Order service PreDestroy");
    }

    public void placeOrder(){
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
