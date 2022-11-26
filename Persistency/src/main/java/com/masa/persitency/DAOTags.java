package com.masa.persitency;

import com.masa.domain.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTags {

    private IConnection connectionDB;
    private UUIDGenerator idGenerator;

    public DAOTags(IConnection connection) {
        this.connectionDB = connection;
    }

    public Tag create(Tag tag) {
        Tag newTag = null;
        idGenerator = new UUIDGenerator();
        String id = String.valueOf(idGenerator.getNewId());

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO tags (id, name, uses)"
                    + " VALUES ('%s', '%s', '%d');",
                    id,
                    tag.getName(),
                    tag.getUsesCount());

            statement.executeUpdate(query);

            connection.close();

            newTag = get(id);
            return newTag;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return newTag;
        }
    }

    public Tag get(String tagId) {
        Tag tag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, uses "
                    + "FROM tags WHERE id = '%s';",
                    tagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                Long uses = result.getLong("uses");
                tag = new Tag(id, name, uses);
            }

            connection.close();
            return tag;

        } catch (SQLException e) {
            Logger.getLogger(DAOTags.class.getName()).log(Level.SEVERE, null, e);
            return tag;
        }
    }

    public Tag edit(Tag tag) {
        Tag editedTag = null;
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE tags SET name='%s', uses=%d WHERE id = '%s';",
                    tag.getName(),
                    tag.getUsesCount(),
                    tag.getId());

            statement.executeUpdate(query);

            connection.close();

            editedTag = get(tag.getId());
            return editedTag;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return editedTag;
        }
    }

//    public boolean delete(Integer tagId) {
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("DELETE FROM tags WHERE id= %d;",
//                    tagId);
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
    public List<Tag> getAll() {
        List<Tag> tagsList = new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, uses FROM tags;");
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                Long uses = result.getLong("uses");
                Tag tag = new Tag(id, name, uses);
                tagsList.add(tag);
            }

            connection.close();
            return tagsList;

        } catch (SQLException e) {
            Logger.getLogger(DAOTags.class.getName()).log(Level.SEVERE, null, e);
            return tagsList;
        }
    }

    public Tag getByName(String name) {
        Tag tag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, name, uses FROM tags WHERE name = '%s';",
                    name);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String existingName = result.getString("name");
                Long uses = result.getLong("uses");
                tag = new Tag(id, existingName, uses);
            }

            connection.close();
            return tag;

        } catch (SQLException e) {
            Logger.getLogger(DAOTags.class.getName()).log(Level.SEVERE, null, e);
            return tag;
        }
    }

}

///**
// *
// * @author Luis Angel Marin
// */
//public class DAOTags {
//    
//    IConnection bdConnection;
//    
//    public DAOTags(IConnection connection){
//        this.bdConnection = connection;
//    }
//   
//    public boolean create(Tag tag) {
//        try {
//            java.sql.Connection connection = this.bdConnection.getConnection();
//            Statement statement = connection.createStatement();
//            String query = String.format("INSERT INTO tags (name, email, phone, gender, birthDate, age) VALUES ('%s', '%s', '%s', '%s', '%s', '%d');",
//                    tag.getName(),
//                    tag.getEmail(),
//                    tag.getPhone(),
//                    tag.getGender(),
//                    tag.getBirthDate(),
//                    tag.getAge());
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
//    public Tag get(Integer tagId) {
//        Tag tag = null;
//        
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM tags WHERE id = %d;",
//                tagId);
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
//                tag = new Tag(id, name, email, phone, gender, birthDate, age);
//            }
//            
//            connection.close();
//            return tag;
//            
//        } catch (SQLException e) {
//            Logger.getLogger(DAOTags.class.getName()).log(Level.SEVERE, null, e);
//            return tag;
//        }
//    }
//
//    public boolean update(Tag tag) {
//         try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("UPDATE tags SET name='%s', email='%s', phone='%s', gender='%s', birthDate='%s', age='%d' WHERE id = %d;",
//                    tag.getName(),
//                    tag.getEmail(),
//                    tag.getPhone(),
//                    tag.getGender(),
//                    tag.getBirthDate(),
//                    tag.getAge(),
//                    tag.getId());
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
//    public boolean delete(Integer tagId) {
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("DELETE FROM tags WHERE id= %d;",
//                    tagId);
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
//    public List<Tag> getAll() {
//        List<Tag> tagsList = new ArrayList<>();
//        
//        try {
//            java.sql.Connection connection = this.connectionDB.connectionDB();
//            Statement statement = connection.createStatement();
//            String query = String.format("SELECT id, name, email, phone, gender, birthDate, age FROM tags;");
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
//                Tag tag = new Tag(id, name, email, phone, gender, birthDate, age);
//                tagsList.add(tag);
//            }
//            
//            connection.close();
//            return tagsList;
//            
//        } catch (SQLException e) {
//            Logger.getLogger(DAOTags.class.getName()).log(Level.SEVERE, null, e);
//            return tagsList;
//        }
//    }
//}
