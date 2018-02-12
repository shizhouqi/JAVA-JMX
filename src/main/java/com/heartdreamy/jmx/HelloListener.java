package com.heartdreamy.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;


public class HelloListener implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {
        try {
            log(handback);
            log(notification.getMessage());
            log(notification.getType());
            log(notification.getSequenceNumber());
            log(notification.getTimeStamp());
            log(notification.getUserData());
            log(notification.getSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void log(Object message){
        System.out.println(message);
    }
}
