/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.businesslogic;

import com.masa.domain.Comment;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class CommentLogic {
  private IPersistency persistency;

    public CommentLogic() {
        persistency = new Persistency();
    }
  
  
    public Comment create(Comment comment){
        return persistency.createComment(comment);
    }
    
     public List<Comment> getCommentsByPost(String idPost){
         return persistency.getCommentsByPost(idPost);
     }
     
    public void delete(String commentId){
         persistency.deleteComment(commentId);
    }
}
