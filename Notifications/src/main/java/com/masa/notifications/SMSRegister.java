/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.notifications;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.ValidationRequest;

/**
 *
 * @author jjavi
 */
public class SMSRegister implements ISMSRegister {

    public final String ACCOUNT_SID;
    public final String AUTH_TOKEN;
    
    public SMSRegister() {
        this.ACCOUNT_SID = System.getenv("ACCOUNT_SID");
        this.AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    }
    
    @Override
    // Nuevo método para registrar usuario
    public void registerUser(String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ValidationRequest validationRequest = ValidationRequest.creator(
                new com.twilio.type.PhoneNumber("+52" + number))
            .setFriendlyName(number)
            .create();

        System.out.println(validationRequest.getFriendlyName());
    }
}
