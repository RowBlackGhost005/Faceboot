package com.masa.authentication;

import com.masa.domain.User;
import com.masa.persitency.IPersistency;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that authenticates a User using the data storage in faceboot database.
 * 
 * @author Luis Marin
 */
public class Local implements IAuthenticationMethod{

    IPersistency persistency;
    
    public Local(IPersistency persistency){
        this.persistency = persistency;
    }
    
    @Override
    public User login(User user){
        
        User loggedUser = null;
        
        try {
            loggedUser = persistency.getUserLogin(user);
        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loggedUser;
    }

}
