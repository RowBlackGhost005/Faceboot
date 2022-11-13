package com.masa.persitency;

import com.masa.domain.RelPostTag;
import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORelPostTag {

    private IConnection connectionDB;
    private UUIDGenerator idGenerator;

    public DAORelPostTag(IConnection connection) {
        this.connectionDB = connection;
    }

    public boolean create(RelPostTag relPostTag) {

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO relPostTag (id_post, id_tag) VALUES ('%s', '%s');",
                    relPostTag.getPostId(),
                    relPostTag.getTagId());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public RelPostTag get(String relPostTagId) {
        RelPostTag relPostTag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, id_post, id_tag FROM relPostTag WHERE id = '%s';",
                    relPostTagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                Long id = result.getLong("id");
                String postId = result.getString("id_post");
                String tagId = result.getString("id_tag");
                relPostTag = new RelPostTag(id, postId, tagId);
            }

            connection.close();
            return relPostTag;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return relPostTag;
        }
    }

//    public boolean edit(User user) {
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("UPDATE users SET name='%s', email='%s', phone='%s', gender='%s', birthDate='%s', password='%s' WHERE id = %d;",
//                    user.getName(),
//                    user.getEmail(),
//                    user.getPhone(),
//                    user.getGender(),
//                    user.getBirthDate(),
//                    user.getPassword(),
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

//    public List<User> getAll() {
//        List<User> usersList = new ArrayList<>();
//
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("SELECT id, name, email, phone, gender, birthDate, password FROM users;");
//            ResultSet result = statement.executeQuery(query);
//
//            while (result.next()) {
//                String id = result.getString("id");
//                String name = result.getString("name");
//                String email = result.getString("email");
//                String phone = result.getString("phone");
//                String gender = result.getString("gender");
//                String birthDate = result.getString("birthDate");
//                String password = result.getString("password");
//                User user = new User(id, name, email, phone, gender, birthDate, password);
//                usersList.add(user);
//            }
//
//            connection.close();
//            return usersList;
//
//        } catch (SQLException e) {
//            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
//            return usersList;
//        }
//    }
    
        public RelPostTag getPostTag(String postId, String tagId) {
        RelPostTag relPostTag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, id_post, id_tag FROM relPostTag WHERE id_post = '%s' AND id_tag = '%s';",
                    postId, tagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                Long id = result.getLong("id");
                String existingPostId = result.getString("id_post");
                String existingTagId = result.getString("id_tag");
                relPostTag = new RelPostTag(id, existingPostId, existingTagId);
            }

            connection.close();
            return relPostTag;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return relPostTag;
        }
    }

}
