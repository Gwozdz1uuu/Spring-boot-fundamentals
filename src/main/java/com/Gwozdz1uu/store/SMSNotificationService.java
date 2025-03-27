package com.Gwozdz1uu.store;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService {
    @Override
    public void send(String message, String recipientEmail){
        System.out.println("Message: " + message + "\n" + "Email: " + recipientEmail);
    }
}
