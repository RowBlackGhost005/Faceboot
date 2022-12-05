/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.persitency;

import com.masa.domain.Comment;
import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class DAOComments {
     private IConnection connectionDB;
    private UUIDGenerator idGenerator;
    
    public DAOComments(IConnection connection) {
        this.connectionDB = connection;
    }
    
    public Comment create(Comment comment){
        Comment newComment = null;
        
        idGenerator = new UUIDGenerator();
        String id = String.valueOf(idGenerator.getNewId());
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO comments (id, id_user, message , date, id_post) VALUES ('%s', '%s', '%s','%s', '%s');",
                    id, comment.getUser().getId(),comment.getMessage(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(comment.getDateTime()),comment.getIdPost());

            statement.executeUpdate(query);

            connection.close();

            newComment = get(id);
            return newComment;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return newComment;
        }
    }
    
     public void delete(String commentId) {
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM comments WHERE id= '%s';", 
                    commentId);

            statement.executeUpdate(query);

            connection.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public Comment get(String idComment){
        
       Comment comment = null;
         try {
             
             java.sql.Connection connection = this.connectionDB.connectionDB();
             Statement statement = connection.createStatement();
             
             String query = String.format("SELECT id, id_user, message , date, id_post "
                     + "FROM comments WHERE id = '%s';",idComment);
             ResultSet result = statement.executeQuery(query);
             
              if (result.next()) {
              String id = result.getString("id");
                String userId = result.getString("message");
                String message = result.getString("id_user");
                Timestamp date = result.getTimestamp("date");
                String postId = result.getString("id_post");
                Date commentDate = new Date(date.getTime());
             comment = new Comment(id, new User(userId), commentDate,message,postId);
              }
              
               connection.close();
                
         } catch (SQLException ex) {
             Logger.getLogger(DAOComments.class.getName()).log(Level.SEVERE, null, ex);
         }
       return comment;
    }
    
    public List<Comment> getByPost(String idPost){
        List<Comment> comments = new ArrayList<>();
        
        try {
             
             java.sql.Connection connection = this.connectionDB.connectionDB();
             Statement statement = connection.createStatement();
             
             String query = String.format("SELECT id, id_user, message , date, id_post "
                     + "FROM comments WHERE id_post = '%s';",idPost);
             ResultSet result = statement.executeQuery(query);
             
             while(result.next()) {
              String id = result.getString("id");
                String message = result.getString("message");
                String idUser = result.getString("id_user");
                Timestamp date = result.getTimestamp("date");
                String postId = result.getString("id_post");
                Date commentDate = new Date(date.getTime());
                Comment comment = new Comment(id, new User(idUser), commentDate,message,postId);
                comments.add(comment);
              }
               connection.close();
                
         } catch (SQLException ex) {
             Logger.getLogger(DAOComments.class.getName()).log(Level.SEVERE, null, ex);
         }
        return comments;
    }
}
