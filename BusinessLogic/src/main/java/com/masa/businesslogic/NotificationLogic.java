/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.businesslogic;

import com.masa.domain.Notification;
import com.masa.notifications.INotifications;
import com.masa.notifications.Notifications;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.util.List;

/**
 *
 * @author jjavi
 */
public class NotificationLogic {
    
    private INotifications notifications;
    private IPersistency persistency;

    public NotificationLogic() {
        this.notifications = new Notifications();
        this.persistency = new Persistency();
    }
    
    public void sendNotification(Notification notification, String provider) {
        if (provider.equalsIgnoreCase("SMS")) {
            notifications.sendSMSNotification(notification);
        } else if (provider.equalsIgnoreCase("E-MAIL")) {
            notifications.sendEmailNotification(notification);
        } else if(provider.equalsIgnoreCase("BOTH")) {
            notifications.sendMultipleTypes(notification);
        } else {
            throw new UnsupportedOperationException("Provider does not exist.");
        }
    }
    
    public void notificationHistory(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void registerUser(String number) {
        notifications.registerNumberUser(number);
    }
    
    public Notification create(Notification notification){
        return persistency.createNotification(notification);
    }
    
    public Notification mirror(Notification notification){
        return persistency.createNotificationMirror(notification);
    }
    
    public Notification get(String notificaitonId){
        return persistency.getNotification(notificaitonId);
    }
    
    public List<Notification> getByUser(String userId){
        return persistency.getNotificationsByUser(userId);
    }
}
