package com.masa.authentication;

import com.masa.domain.User;
import com.masa.persitency.IPersistency;

/**
 * Class that authenticates an user using one of the multiple authentication
 * methods.
 *
 * @author Luis Marin
 */
public class Authentication implements IAuthentication {

    private IPersistency persistency;
    
    private Local localAuth;
    
    private Google googleAuth;

    public Authentication(IPersistency persistency) {
        this.persistency = persistency;
        this.localAuth = new Local(persistency);
        this.googleAuth = new Google();
    }

    @Override
    public User authenticate(User user, String type) {

        User authenticatedUser = null;
        
        switch (type.toLowerCase()) {

            case "local" :
                authenticatedUser = localAuth.login(user);
                break;

            case "google" : 
                authenticatedUser = googleAuth.login(user);
                
//                authenticatedUser = persistency.createUser(user);
                
                break;

            //TODO AUTHENTICATION WITH TWITTER
            case "twitter" : 
                
                break;
        }
        
        return authenticatedUser;
    }

}
