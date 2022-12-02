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

    private INotificator notificator;
    
    public Notifications() {
    }
    
    @Override
    public void sendSMSNotification(Notification notification) {
        notificator = new SMSProvider(notificator);
        notificator.sendNotification(notification);
    }

    @Override
    public void sendEmailNotification(Notification notification) {
        notificator = new EmailProvider(notificator);
        notificator.sendNotification(notification);
    }

    @Override
    public void sendMultipleTypes(Notification notification) {
        sendSMSNotification(notification);
        sendEmailNotification(notification);
    }  

    @Override
    public void registerNumberUser(String number) { // Nuevo método de registro de usuarios en la API
        ISMSRegister register = new SMSRegister();
        register.registerUser(number);
    }
}
