package com.masa.businesslogic;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
<<<<<<< HEAD
import com.masa.utils.IObserver;
=======
import java.io.IOException;
>>>>>>> origin/feature
import java.util.List;

/**
 * Interface that defines all the operations that the logic layer can do.
 * 
 * @author Luis Angel Marin
 */
public interface IBusinessLogic {
    
    public User registerUser(User user, boolean broadcast);
  
    public User login(User user) throws Exception;
    
    public User getUser(String userId);
    
    public List<User> getAllUsers();
    
<<<<<<< HEAD
    public void createPost(Post post, boolean broadcast);
    
    public void createPost(Post post, Tag tags, boolean broadcast);
    
    public void subscribeGUINotifications(IObserver observer);
=======
    public void createPost(Post post) throws IOException;
>>>>>>> origin/feature

    public void unSubscribeGUINotifications(IObserver observer);
}
