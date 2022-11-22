package com.mycompany.gui;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class GUIControllerLogic {
    
    private static IBusinessLogic businessLogic;
    
    public GUIControllerLogic(){
        
        if(businessLogic == null){
            businessLogic = BusinessLogic.createBusinessLogic();
        }
    }
    
    public void registerUser(User user) {
        
        businessLogic.registerUser(user , true);
    }
    
   
    
}
