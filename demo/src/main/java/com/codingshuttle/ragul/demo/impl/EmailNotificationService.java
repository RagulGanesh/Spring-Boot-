package com.codingshuttle.ragul.demo.impl;

import com.codingshuttle.ragul.demo.NotificationService;

public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email Sending : "+message);
    }
}
