package com.masa.businesslogic;

import com.masa.domain.User;

/**
 * Interface that defines all the operations that the logic layer can do.
 * 
 * @author Luis Angel Marin
 */
public interface IBusinessLogic {
    
    public User registerUser(User user, boolean broadcast);
}
