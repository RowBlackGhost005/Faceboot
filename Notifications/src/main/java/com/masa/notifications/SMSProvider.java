/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.notifications;

import com.masa.domain.Notification;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author jjavi
 */
public class SMSProvider extends BaseNotificator {
    
    public final String ACCOUNT_SID;
    public final String AUTH_TOKEN;;

    public SMSProvider(INotificator notificator) {
        super(notificator);
        this.ACCOUNT_SID = "";
        this.AUTH_TOKEN = "";
        
    }

    @Override
    public void sendNotification(Notification notification) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+52" + notification.getTo().getPhone()),
                new com.twilio.type.PhoneNumber("+19519441713"),
                notification.getMessage())
            .create();

        System.out.println(message.getSid());
    }
}
