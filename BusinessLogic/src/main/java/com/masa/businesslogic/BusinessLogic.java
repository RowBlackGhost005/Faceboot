package com.masa.businesslogic;

import com.masa.communication.Communication;
import com.masa.domain.User;
import domain.Request;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class BusinessLogic implements IBusinessLogic{

    private UserLogic userLogic;
    
    private Communication communication;
    
    public BusinessLogic(){
        this.userLogic = new UserLogic();
        this.communication = new Communication("Peer" , this);
    }
    
    @Override
    public User registerUser(User user, boolean broadcast) {
        
        try {
            user = userLogic.registerUser(user);
        } catch (Exception ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (broadcast) {
            Request request = new Request("registeruser", "RegisterUser");

            request.append(user, "user");

            communication.sendToAllPeers(request);
        }

        return user;
    }
    
    @Override
    public User login(User user) throws Exception {
        return userLogic.login(user);
    }


}
