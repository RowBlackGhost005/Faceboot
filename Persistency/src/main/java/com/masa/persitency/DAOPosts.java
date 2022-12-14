package com.masa.persitency;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPosts {

    private IConnection connectionDB;
    private UUIDGenerator idGenerator;

    public DAOPosts(IConnection connection) {
        this.connectionDB = connection;
    }

    public Post create(Post post) {
        Post newPost = null;
        
        idGenerator = new UUIDGenerator();
        String id = String.valueOf(idGenerator.getNewId());
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO posts (id, message, image_path , user, date) VALUES ('%s', '%s', '%s','%s', '%s');", id, post.getMessage(), post.getImagePath(), post.getUser().getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(post.getDateTime()));

            statement.executeUpdate(query);

            connection.close();

            newPost = get(id);
            return newPost;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return newPost;
        }
    }
    
    public Post mirrorPost(Post post){
        
        try{
            
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO posts (id, message, image_path , user , date) VALUES ('%s', '%s', '%s','%s' , '%s');", post.getId() , post.getMessage(), post.getImagePath(), post.getUser().getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(post.getDateTime()));
            
            statement.executeUpdate(query);

            connection.close();
            
            return post;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return post;
        }
    }

    public Post get(String postId) {
        Post post = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();

            String query = String.format("SELECT id, message, image_Path, user , date "
                    + "FROM posts WHERE id = '%s';",
                    postId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String id = result.getString("id");
                String message = result.getString("message");
                String userId = result.getString("user");
                String imagePath = result.getString("image_Path");
                post = new Post(id, message, imagePath, new User(userId));
                User user = new User();
                user.setId(userId);
                
               Timestamp date = result.getTimestamp("date");
                
                Date postDate = new Date();
                
                if(date != null){
                    postDate = new Date(date.getTime());
                }
                
                post = new Post(id, message, imagePath, user , postDate);
            }
            
            
            connection.close();
            return post;

        } catch (SQLException e) {
            Logger.getLogger(DAOPosts.class.getName()).log(Level.SEVERE, null, e);
            return post;
        }
    }

    public Post edit(Post post) {
       
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE posts SET message='%s', image_path='%s', user='%s', date='%s' WHERE id = '%s';",
                    post.getMessage(),
                    post.getImagePath(),
                    post.getUser().getId(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(post.getDateTime().getTime()),
                    post.getId());

            int registries = statement.executeUpdate(query);

            connection.close();

            return get(post.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void delete(String postId) {
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM posts WHERE id= '%s'; DELETE FROM comments WHERE id_post= '%s';",
                    postId,postId);

            statement.executeUpdate(query);

            connection.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public List<Post> getAll() {
        List<Post> postsList = new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            
            String query = String.format("SELECT id, message, image_path, user , date FROM posts;");

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");
                String imagePath = result.getString("image_path");
                String message = result.getString("message");
                String idUser = result.getString("user");
                Timestamp date = result.getTimestamp("date");
                
                Date postDate = new Date();
                
                if(date != null){
                    postDate = new Date(date.getTime());
                }
                
                User user = new User();
                user.setId(idUser);
                Post post = new Post(id, message, imagePath, user, postDate);
                postsList.add(post);
            }

            connection.close();
            return postsList;

        } catch (SQLException e) {
            Logger.getLogger(DAOPosts.class.getName()).log(Level.SEVERE, null, e);
            return postsList;
        }
    }
    
    public List<Post> getByTag(Tag tag){
        
        List<Post> postsList = new ArrayList<Post>();
        
       try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            
            String query = String.format("SELECT posts.id, posts.message, posts.image_path, posts.user , date FROM posts INNER JOIN relPostTag ON posts.id = relPostTag.id_post AND relPostTag.id_tag = '%s';", 
                                            tag.getId());

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String id = result.getString("id");
                String imagePath = result.getString("image_path");
                String message = result.getString("message");
                String idUser = result.getString("user");
                Timestamp date = result.getTimestamp("date");
                
                Date postDate = new Date();
                
                if(date != null){
                    postDate = new Date(date.getTime());
                }
                
                User user = new User();
                user.setId(idUser);
                Post post = new Post(id, message, imagePath, user, postDate);
                postsList.add(post);
            }

            connection.close();
            return postsList;

        } catch (SQLException e) {
            Logger.getLogger(DAOPosts.class.getName()).log(Level.SEVERE, null, e);
            return postsList;
        }
    }
}
