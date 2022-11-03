package com.masa.gui;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.User;

public class GUIControllerLogic {

    public void registerUser(User user) {
        IBusinessLogic logic = new BusinessLogic();

        logic.registerUser(user);
    }
}
