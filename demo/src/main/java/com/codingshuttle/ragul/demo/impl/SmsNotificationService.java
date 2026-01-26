package com.codingshuttle.ragul.demo.impl;

import com.codingshuttle.ragul.demo.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
@Qualifier("smsNotif")
//@ConditionalOnProperty(name = "notification.type",havingValue="sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("SMS Sending : "+message);
    }
}
