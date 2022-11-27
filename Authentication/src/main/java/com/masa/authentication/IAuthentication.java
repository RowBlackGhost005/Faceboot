package com.masa.authentication;

import com.masa.domain.User;
/**
 * Defines the behaviour of this Authentication component
 * 
 * @author Luis Marin
 */
public interface IAuthentication {
    
    /**
     * Authenticates the given credentials in the User parameter.
     * 
     * If the login is authorized, this method will return the complete the user
     * with all its information.
     * 
     * A User with ID means the authentication process was successfull
     * 
     * @param user User to authenticate.
     * @param type Type of authentication method to use with the credentials.
     * @return User with all its information if authenticated, null otherwise.
     */
    public User authenticate(User user , String type);
}
