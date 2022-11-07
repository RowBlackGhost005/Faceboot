package logic;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.User;

public class GUILogic{

    private static IBusinessLogic logic;
    
    public static void createBusinessLogic(){
        if(logic == null){
            logic = new BusinessLogic();
        }
    }
    
    public static void registerUser(User user) {
        logic.registerUser(user , true);
    }
}
