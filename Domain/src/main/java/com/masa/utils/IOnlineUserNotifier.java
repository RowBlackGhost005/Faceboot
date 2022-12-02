package com.masa.utils;

import com.masa.domain.User;

/**
 * Observable of a new Logged user to update the GUI.
 * @author Luis Marin
 */
public interface IOnlineUserNotifier {
    
    /**
     * Adds a new observer to notify with the new post that had been made.
     * @param observer Obserer to subscribe to this notifier.
     */
    public void addOnlineUserObserver(IOnlineUserObserver observer);
    
    /**
     * Removes the observer of the notify list.
     * @param observer Observer to unsubscribe.
     */
    public void removeOnlineUserObserver(IOnlineUserObserver observer);
    
    /**
     * Notifies all subscribers that a new post has been made and sends said post.
     */
    public void notifyOnlineUser(User user, String type);
}
