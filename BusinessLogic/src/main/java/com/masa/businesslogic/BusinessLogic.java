package com.masa.businesslogic;

import com.masa.communication.CommHandler;
import com.masa.communication.Communication;
import com.masa.domain.User;
import domain.Request;

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
    public User registerUser(User user) {
        
        System.out.println("HEY");
        user = userLogic.registerUser(user);
        
        
        Request request = new Request("registeruser" , "RegisterUser");
        
        request.append(user, "registeruser");
        
        communication.sendToAllPeers(request);
        
        return user;
    }
    
}
