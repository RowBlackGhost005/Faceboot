package logic;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.User;

public class GUIControllerLogic{

    private static IBusinessLogic logic;
    
    public static void createBusinessLogic(){
        if(logic == null){
            logic = new BusinessLogic();
        }
    }
    
    public static void registerUser(User user) throws Exception {
        logic.registerUser(user , true);
    }
}
