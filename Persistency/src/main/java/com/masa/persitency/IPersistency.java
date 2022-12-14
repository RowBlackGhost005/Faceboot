package com.masa.persitency;

import com.masa.domain.Comment;
import com.masa.domain.Log;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.RelPostUser;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.sql.SQLException;
import java.util.List;

public interface IPersistency {

    public User createUser(User user);
    
    public User mirrorUser(User user);
    
    public void editUser(User user);
    
    public User getUser(String userId);

    public List<User> getAllUsers();
    
    public User getUserLogin(User user) throws SQLException;   
    
    public User getUserByEmail(String email);
    
    public User getUserByPhone(String phone);
    
    public Post createPost(Post post);
    
    public Post mirrorPost(Post post);
    
    public Post getPost(String postId);
    
    public List<Post> getAllPost();
    
    public Tag createTag(Tag tag);
    
    public Tag mirrorTag(Tag tag);
    
    public Tag getTag(String tagId);
    
    public Tag editTag(Tag tag);
    
    public Post editPost(Post post);
    
    public void deletePost(String post);
    
    public List<Tag> getAllTags();
    
    public List<Tag> getTagsByPost(String postId);
    
    public Tag getTagByName(String name);
    
    public Boolean createRelPostTag(RelPostTag relPostTag);
    
    public Boolean createRelPostUser(RelPostUser relPostUser);
    
    public List<User> getUsersTagged(String postId);
    
    public RelPostTag getRelPostTag(String relPostTagId);
    
    public RelPostTag getRelPostTag(String postId, String tagId);
    
    public List<Log> getAllLogs();
    
    public List<Post> getPostByTag(Tag tag);
    
    public Notification createNotification(Notification notification);
    
    public Notification getNotification(String notificaitonId);
    
    public List<Notification> getNotificationsByUser(String userId);
    
    public Notification createNotificationMirror(Notification notification);
   
    public Comment createComment(Comment comment);
    
    public void deleteComment(String commentId);
    
    public List<Comment> getCommentsByPost(String idPost);
    
    
}
