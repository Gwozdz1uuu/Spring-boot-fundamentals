package com.Gwozdz1uu.store.OldStaff;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message,String recipientEmail){
        System.out.println("Recipient: " + recipientEmail + "\n"
                + "Message: " + message +"\n"
                + "Port: " + port + "\n"
                + "Host: " + host);
    }
}
