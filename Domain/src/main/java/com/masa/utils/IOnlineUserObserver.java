package com.masa.utils;

import com.masa.domain.User;

/**
 *
 * @author Luis Marin
 */
public interface IOnlineUserObserver extends IObserver{
    
    public void updateOnlineUser(User user, String type);
}
