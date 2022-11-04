package interfaces;

import entities.User;

public interface IBusinessLogic {

    public User registerUser(User user) throws Exception;

    public User login(User user) throws Exception;
    

}
