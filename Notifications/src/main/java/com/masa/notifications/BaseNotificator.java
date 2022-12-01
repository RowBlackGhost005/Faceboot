package com.masa.notifications;

import com.masa.domain.Notification;

/**
 *
 * @author jjavi
 */
public abstract class BaseNotificator implements INotificator {
    
    protected INotificator notificator;

    public BaseNotificator(INotificator notificator) {
        this.notificator = notificator;
    }
    
    @Override
    public void sendNotification(Notification notification) {
        notificator.sendNotification(notification);
    }
}
