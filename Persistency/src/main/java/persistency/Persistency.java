package persistency;

import interfaces.IPersistency;
import entities.User;
import java.sql.SQLException;
import java.util.List;

public class Persistency implements IPersistency {

    private DAOsFactory daosFactory;
    private DAOUsers users;

    public Persistency() {
        daosFactory = new DAOsFactory();
        users = daosFactory.createDAOUsers();
    }

    @Override
    public void CreateUser(User user) {
        users.create(user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getUserLogin(User user) throws SQLException {
        return users.getByLogin(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.getByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return users.getByPhone(phone);
    }

}
