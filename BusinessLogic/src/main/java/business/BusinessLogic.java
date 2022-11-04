package business;

import interfaces.IBusinessLogic;
import entities.User;

public class BusinessLogic implements IBusinessLogic {

    private UserLogic userLogic;

    public BusinessLogic() {
        userLogic = new UserLogic();
    }

    @Override
    public User registerUser(User user) throws Exception {
        return userLogic.registerUser(user);
    }

    @Override
    public User login(User user) throws Exception {
        return userLogic.Login(user);
    }

}
