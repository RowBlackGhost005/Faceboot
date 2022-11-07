package interfaces;

import entities.User;
import java.sql.SQLException;
import java.util.List;

public interface IPersistency {

    public void CreateUser(User user);
    
    public User getUser(String userId);

    public List<User> getAllUsers();
    
    public User getUserLogin(User user) throws SQLException;   
    
    public User getUserByEmail(String email);
    
    public User getUserByPhone(String phone);

}
