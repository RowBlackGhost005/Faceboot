package com.masa.businesslogic;

import com.masa.domain.User;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;

/**
 *
 * @author Luis Angel Marin
 */
public class UserLogic {
    
    IPersistency persistency;
    
    public UserLogic(){
        this.persistency = new Persistency();
    }
    
    public User registerUser(User user){
        persistency.createUser(user);
        
        return user;
    }
}
