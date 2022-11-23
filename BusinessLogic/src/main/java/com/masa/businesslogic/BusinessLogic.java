package com.masa.businesslogic;

import com.masa.authentication.Google;
import com.masa.communication.CommHandler;
import com.masa.communication.ICommHandler;
import com.masa.domain.Post;
import com.masa.domain.PostTransferObject;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObservable;
import com.masa.utils.IObserver;
import domain.Request;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class BusinessLogic implements IBusinessLogic {

    private UserLogic userLogic;
    private PostLogic postLogic;
    private TagLogic tagLogic;

    private User userLogged;
    private ICommHandler communication;

    private BusinessLogic() {
        this.userLogic = new UserLogic();
        this.postLogic = new PostLogic();
        this.tagLogic = new TagLogic();
    }
    
    public static IBusinessLogic createBusinessLogic(){
        BusinessLogic business = new BusinessLogic();
        
        business.initCommunication(business);
        
        return business;
    }
    
    private void initCommunication(IBusinessLogic businessLogic){
        this.communication = new CommHandler(businessLogic);
        communication.initCommunication();
    }

    @Override
    public User registerUser(User user, boolean broadcast) {

        try {
            user = userLogic.registerUser(user);
        } catch (Exception ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (broadcast) {
            Request request = new Request("registeruser", "RegisterUser");

            request.append(user, "user");

            CommunicationThread requestThread = new CommunicationThread(request, communication);
            
            Thread thread = new Thread(requestThread);

            thread.start();
            
        }

        return user;
    }
    
    @Override
     public User registerExternalUser(User user, boolean broadcast) {

        try {
            user = userLogic.registerUser(user);
        } catch (Exception ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (broadcast) {
            Request request = new Request("registerexternaluser", "RegisterExternalUser");

            request.append(user, "user");

            CommunicationThread requestThread = new CommunicationThread(request, communication);
            
            Thread thread = new Thread(requestThread);

            thread.start();
            
        }

        return user;
    }
    

    @Override
    public User login(User user) throws Exception {
        return userLogic.login(user);
    }
    
    @Override
    public User loginWith(String method) throws Exception{
        switch(method){
            case "GOOGLE":return userLogic.loginUser(new Google());
        }
        return null;
    }

    @Override
    public User getUser(String userId) {
        return userLogic.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userLogic.getAll();
    }

     @Override
    public List<Post> getAllPost() {
        return postLogic.getAllPost();
    }
    @Override
    public void createPost(Post post, boolean broadcast) throws IOException{
          
        Post createdPost = postLogic.create(post, tagLogic);
        
             
        if(broadcast){
            Request request = new Request("registerpost", "RegisterPost");
            
            PostTransferObject postTransferObject = new PostTransferObject();
            postTransferObject.convertPost(createdPost);
            
            request.append(postTransferObject, "post");
            
            CommunicationThread requestThread = new CommunicationThread(request, communication);
            
            Thread thread = new Thread(requestThread);

            thread.start();
        }
    }

    @Override
    public void createPost(Post post, Tag tag, boolean broadcast) throws IOException{
        
        Post createdPost = postLogic.create(post, tagLogic);
        
        if(broadcast){
            Request request = new Request("registerpost", "RegisterPost");
            
            PostTransferObject postTransferObject = new PostTransferObject();
            postTransferObject.convertPost(createdPost);
            
            request.append(postTransferObject, "post");
            request.append(tag, "tag");
            
            CommunicationThread requestThread = new CommunicationThread(request, communication);
            
            Thread thread = new Thread(requestThread);

            thread.start();
        }
    }

    @Override
    public void subscribeGUINotifications(IObserver observer) {
        ((IObservable) communication).addObserver(observer);
    }
    
    @Override
    public void unSubscribeGUINotifications(IObserver observer){
        ((IObservable) communication).removeObserver(observer);
    }
    
    @Override
    public void createPost(Post post) throws IOException {
        postLogic.create(post, tagLogic);
    }

    @Override
    public void setUserLogged(User user){
        this.userLogged=user;
    }
    
    @Override
    public User getUserLogged(){
        return this.userLogged;
    }
}
