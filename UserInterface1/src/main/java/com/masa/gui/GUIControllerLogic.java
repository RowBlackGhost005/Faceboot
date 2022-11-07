package com.masa.gui;

import com.masa.domain.User;

public class GUIControllerLogic {

    public void registerUser(User user) {
        IBusinessLogic logic = new BusinessLogic();

        logic.registerUser(user , true);
    }
}
