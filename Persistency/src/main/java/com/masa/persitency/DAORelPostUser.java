/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.persitency;

import com.masa.domain.RelPostUser;
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
 * @author Andrea
 */
public class DAORelPostUser {
     private IConnection connectionDB;

    public DAORelPostUser(IConnection connection) {
        this.connectionDB = connection;
    }

    public boolean create(RelPostUser relPostUser) {

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO relPostUser (id_post, id_user) VALUES ('%s', '%s');",
                    relPostUser.getPostId(),
                    relPostUser.getUserId());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public RelPostUser get(String relPostTagId) {
        RelPostUser relPostUser = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, id_post, id_user FROM relPostUser WHERE id = '%s';",
                    relPostTagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                Long id = result.getLong("id");
                String postId = result.getString("id_post");
                String userId = result.getString("id_user");
                relPostUser = new RelPostUser(id, postId, userId);
            }

            connection.close();
            return relPostUser;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return relPostUser;
        }
    }
    
       public List<User> getUsersTagged(String postId) {
        List<User> users=new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id_user FROM relPostUser WHERE id_post = '%s';",
                    postId);
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
 
                String userId = result.getString("id_user");
                User user = new User();
                user.setId(userId);
                users.add(user);
            }

            connection.close();
            return users;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return users;
        }
    }

}
