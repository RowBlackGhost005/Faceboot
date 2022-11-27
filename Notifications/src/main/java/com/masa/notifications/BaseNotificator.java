/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.masa.notifications;

import com.masa.domain.Notification;

/**
 *
 * @author jjavi
 */
public interface BaseNotificator {
    
    public void sendNotification(Notification notification);
}
