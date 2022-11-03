package com.masa.persitency;

import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class DAOUsers {
    
    IConnection connectionDB;
    
    public DAOUsers(){
        this.connectionDB = new SQLConnection();
    }
   
    public boolean create(User user) {
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO users (name, email, phone, gender, birthDate, age) VALUES ('%s', '%s', '%s', '%s', '%s', '%d');",
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getGender(),
                    user.getBirthDate(),
                    user.getAge());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public User get(Integer userId) {
        User user = null;
        
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM users WHERE id = %d;",
                userId);
            ResultSet result = statement.executeQuery(query);
            
            if(result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                Integer age = result.getInt("age");
                user = new User(id, name, email, phone, gender, birthDate, age);
            }
            
            connection.close();
            return user;
            
        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return user;
        }
    }

    public boolean update(User user) {
         try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE users SET name='%s', email='%s', phone='%s', gender='%s', birthDate='%s', age='%d' WHERE id = %d;",
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getGender(),
                    user.getBirthDate(),
                    user.getAge(),
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
            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM users;");
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String birthDate = result.getString("birthDate");
                Integer age = result.getInt("age");
                User user = new User(id, name, email, phone, gender, birthDate, age);
                usersList.add(user);
            }
            
            connection.close();
            return usersList;
            
        } catch (SQLException e) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, e);
            return usersList;
        }
    }
}
