package com.Gwozdz1uu.store.OldStaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final NotificationService notificationService;

    @Autowired
    public NotificationManager(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void sendNotification(String message, String recipientEmail){
        notificationService.send(message,recipientEmail);
    }
}
