package com.masa.persitency;

import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsers {

    private IConnection connectionDB;

    public DAOUsers(IConnection connection) {
        this.connectionDB = connection;
    }

    public boolean create(User user) {

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query
                    = String.format("INSERT INTO users (id, name, email, phone, "
                            + "gender, birthDate, password) "
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                            UUIDGenerator.getNewId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone(),
                            user.getGender(),
                            user.getBirthDate(),
                            user.getPassword());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public User get(String userId) {
        User user = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, email, phone, gender, "
                    + "birthDate, password FROM users "
                    + "WHERE id = '%s';",
                    userId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                String password = result.getString("password");
                user = new User(id, name, email, phone, gender, birthDate, password);
            }

            connection.close();
            return user;

        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return user;
        }
    }

    public boolean edit(User user) {
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE users SET name='%s', email='%s', "
                    + "phone='%s', gender='%s', birthDate='%s', password='%s' "
                    + "WHERE id = '%s';",
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getGender(),
                    user.getBirthDate(),
                    user.getPassword(),
                    user.getId());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(Integer userId) {
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM users WHERE id= %d;",
                    userId);

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, email, phone, gender, "
                    + "birthDate, password FROM users;");
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                String password = result.getString("password");
                User user = new User(id, name, email, phone, gender, birthDate, password);
                usersList.add(user);
            }

            connection.close();
            return usersList;

        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return usersList;
        }
    }

    public User getByLogin(User user) throws SQLException {
        User existingUser = null;
        String query = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            if (user.getEmail() != null) {
                query = String.format("SELECT id, name, email, phone, gender, "
                        + "birthDate, password FROM users "
                        + "WHERE email = '%s' AND password = '%s';",
                        user.getEmail(), user.getPassword());
            } else if (user.getPhone() != null) {
                query = String.format("SELECT id, name, email, phone, gender, "
                        + "birthDate, password FROM users "
                        + "WHERE phone = '%s' AND password = '%s';",
                        user.getPhone(), user.getPassword());
            }
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String existingEmail = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                String password = result.getString("password");
                existingUser = new User(id, name, existingEmail, phone, gender,
                        birthDate, password);
            }

            connection.close();
            return existingUser;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public User getByEmail(String email) {
        User user = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, email, phone, gender, "
                    + "birthDate, password FROM users WHERE email = '%s';",
                    email);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String existingEmail = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                String password = result.getString("password");
                user = new User(id, name, existingEmail, phone, gender, birthDate, 
                        password);
            }

            connection.close();
            return user;

        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return user;
        }
    }

    public User getByPhone(String phone) {
        User user = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, email, phone, gender, "
                    + "birthDate, password FROM users WHERE phone = '%s';",
                    phone);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String existingPhone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                String password = result.getString("password");
                user = new User(id, name, email, existingPhone, gender, birthDate, 
                        password);
            }

            connection.close();
            return user;

        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return user;
        }
    }

}

///**
// *
// * @author Luis Angel Marin
// */
//public class DAOUsers {
//    
//    IConnection bdConnection;
//    
//    public DAOUsers(IConnection connection){
//        this.bdConnection = connection;
//    }
//   
//    public boolean create(User user) {
//        try {
//            java.sql.Connection connection = this.bdConnection.getConnection();
//            Statement statement = connection.createStatement();
//            String query = String.format("INSERT INTO users (name, email, phone, gender, birthDate, age) VALUES ('%s', '%s', '%s', '%s', '%s', '%d');",
//                    user.getName(),
//                    user.getEmail(),
//                    user.getPhone(),
//                    user.getGender(),
//                    user.getBirthDate(),
//                    user.getAge());
//
//            int registries = statement.executeUpdate(query);
//
//            connection.close();
//
//            return registries == 1;
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
//
//    public User get(Integer userId) {
//        User user = null;
//        
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM users WHERE id = %d;",
//                userId);
//            ResultSet result = statement.executeQuery(query);
//            
//            if(result.next()) {
//                Integer id = result.getInt("id");
//                String name = result.getString("name");
//                String email = result.getString("email");
//                String phone = result.getString("phone");
//                String gender = result.getString("gender");
//                String birthDate = result.getString("birthDate");
//                Integer age = result.getInt("age");
//                user = new User(id, name, email, phone, gender, birthDate, age);
//            }
//            
//            connection.close();
//            return user;
//            
//        } catch (SQLException e) {
//            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
//            return user;
//        }
//    }
//
//    public boolean update(User user) {
//         try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("UPDATE users SET name='%s', email='%s', phone='%s', gender='%s', birthDate='%s', age='%d' WHERE id = %d;",
//                    user.getName(),
//                    user.getEmail(),
//                    user.getPhone(),
//                    user.getGender(),
//                    user.getBirthDate(),
//                    user.getAge(),
//                    user.getId());
//
//            int registries = statement.executeUpdate(query);
//
//            connection.close();
//
//            return registries == 1;
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
//
//    public boolean delete(Integer userId) {
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("DELETE FROM users WHERE id= %d;",
//                    userId);
//
//            int registries = statement.executeUpdate(query);
//
//            connection.close();
//
//            return registries == 1;
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
//
//    public List<User> getAll() {
//        List<User> usersList = new ArrayList<>();
//        
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM users;");
//            ResultSet result = statement.executeQuery(query);
//            
//            while(result.next()) {
//                Integer id = result.getInt("id");
//                String name = result.getString("name");
//                String email = result.getString("email");
//                String phone = result.getString("phone");
//                String gender = result.getString("gender");
//                String birthDate = result.getString("birthDate");
//                Integer age = result.getInt("age");
//                User user = new User(id, name, email, phone, gender, birthDate, age);
//                usersList.add(user);
//            }
//            
//            connection.close();
//            return usersList;
//            
//        } catch (SQLException e) {
//            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
//            return usersList;
//        }
//    }
//}
