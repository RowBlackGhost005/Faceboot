package com.masa.notifications;

import com.masa.domain.Notification;

/**
 *
 * @author jjavi
 */
public interface BaseNotificator {
    
    public void sendNotification(Notification notification);
}
