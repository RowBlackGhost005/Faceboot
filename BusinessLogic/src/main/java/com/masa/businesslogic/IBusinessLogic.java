package com.masa.businesslogic;

import com.masa.domain.Comment;
import com.masa.domain.Log;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import com.masa.utils.IOnlineUserObserver;
import com.masa.utils.IPostObserver;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Logger;

/**
 * Interface that defines all the operations that the logic layer can do.
 *
 * @author Luis Angel Marin
 */
public interface IBusinessLogic {
    
    public User registerUser(User user, boolean broadcast) throws Exception;
    
    public User registerExternalUser(User user, boolean broadcast);
  
    public User login(User user, String method) throws Exception;
    
    public User loginWith(String method) throws Exception;
    
    public User editUser(User user, boolean broadcast) throws Exception;

    public User getUser(String userId);

    public List<User> getAllUsers();
    
    public void createPost(Post post, boolean broadcast) throws IOException;
    
    public void createComment(Comment comment, boolean broadcast)throws IOException;
    
    public void createPost(Post post, Tag tags, boolean broadcast) throws IOException;
    
    public void createPost(Post post) throws IOException;
    
    public Post editPost(Post post)throws IOException;
    
    public List<Post> getAllPost();
    
    public void setUserLogged(User user);
    
    public User getUserLogged();
    
    public Logger getLog();
    
    public List<Log> getAllLogs();
    
    public void subscribePostNotifications(IPostObserver postObserver);
    
    public void unsbuscribePostNotifications(IPostObserver postObserver);
    
    public void subscribeCommentNotification(IObserver commObserver);
    
    public void unsubscribeCommentNotification(IObserver commObserver);
    
    public void subscribeOnlineUserNotification(IOnlineUserObserver onlineUserObserver);
    
    public void unsubscribeOnlineUserNotification(IOnlineUserObserver onlineUserObserver);

    public void sendNotification(Notification notification, String provider);
    
    public void getAllOnlineUsers();
    
    public void logout();
    
    public List<Post> getPostByTag(String tag);
    
    public Notification createNotification(Notification notification, boolean broadcast);
    
    public Notification getNotification(String notificaitonId);
    
    public List<Notification> getNotificationsByUser(String userId);
}
