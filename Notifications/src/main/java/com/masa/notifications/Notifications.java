/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.notifications;

import com.masa.domain.Notification;

/**
 *
 * @author jjavi
 */
public class Notifications implements INotifications {

    private BaseNotificator notificator;
    
    public Notifications() {
    }
    
    @Override
    public void sendSMSNotification(Notification notification) {
        notificator = new SMSProvider();
        notificator.sendNotification(notification);
    }

    @Override
    public void sendEmailNotification(Notification notification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendMultipleTypes(Notification notification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }  
}
