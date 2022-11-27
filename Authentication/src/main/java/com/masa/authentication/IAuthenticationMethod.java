package com.masa.authentication;

import com.masa.domain.User;


/**
 *
 * @author Andrea
 */
public interface IAuthenticationMethod {
    
    public User login(User user) throws Exception;
}
