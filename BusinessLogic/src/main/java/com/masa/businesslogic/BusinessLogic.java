package com.masa.businesslogic;

import com.masa.authentication.Google;
import com.masa.communication.CommHandler;
import com.masa.communication.ICommHandler;
import com.masa.domain.Log;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.PostTransferObject;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.persitency.Persistency;
import com.masa.utils.IObserver;
import com.masa.utils.IPostNotifier;
import domain.Request;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that holds all the functionalities of the application.
 *
 * @author Luis Angel Marin
 */
public class BusinessLogic implements IBusinessLogic {

    private Logger LOGGER;
    private UserLogic userLogic;
    private PostLogic postLogic;
    private TagLogic tagLogic;
    private NotificationLogic notificationLogic;
    private LogsLogic logsLogic;

    private User userLogged;
    private ICommHandler communication;

    public BusinessLogic() {
        LOGGER = LogManager.getLogger(BusinessLogic.class.getName());
        this.userLogic = new UserLogic();
        this.postLogic = new PostLogic();
        this.tagLogic = new TagLogic();
        this.notificationLogic = new NotificationLogic();
        this.logsLogic = new LogsLogic();
    }

    public static IBusinessLogic createBusinessLogic() {
        BusinessLogic business = new BusinessLogic();

        business.initCommunication(business);

        return business;
    }

    private void initCommunication(IBusinessLogic businessLogic) {
        this.communication = new CommHandler(businessLogic);
        communication.initCommunication();
    }

    @Override
    public User registerUser(User user, boolean broadcast)  {

        try {
            user = userLogic.registerUser(user);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        if (broadcast) {
            Request request = new Request("registeruser", "RegisterUser");

            request.append(user, "user");

            initCommunicationThread(request);

            CommunicationThread requestThread = new CommunicationThread(request, communication);

            Thread thread = new Thread(requestThread);

            thread.start();

        }
        LOGGER.info("The user: "+user.getName()+" has succesfuly Signed Up");
        return user;
    }

    @Override
    public User registerExternalUser(User user, boolean broadcast) {

        try {
            user = userLogic.registerExternalUser(user);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        if (broadcast) {
            Request request = new Request("registerexternalusers", "RegisterExternalUser");

            request.append(user, "user");

            initCommunicationThread(request);
        }
        LOGGER.info("The user: "+user.getName()+" has succesfuly Signed Up with Google");
        return user;
    }

    @Override
    public User login(User user, String method) throws Exception {
        
        
        User userLogged = userLogic.login(user, method);
        
        setUserLogged(userLogged);
        
        LOGGER.info("The user: "+userLogged.getName()+" has Logged in");
        return userLogged;
    }

    @Override
    public User loginWith(String method) throws Exception {
        User user;
        switch (method) {
            case "GOOGLE":
                
                user = userLogic.loginUser(new Google());
                LOGGER.info("The user: "+user.getName()+"has succesfuly Signed Up");
                return user;
        }
        
        return null;
    }
    
    @Override
    public User editUser(User user, boolean broadcast) throws Exception {

        user = userLogic.editUser(user);

        if (broadcast) {
            Request request = new Request("edituser", "editUser");

            request.append(user, "user");

            initCommunicationThread(request);
        }

        LOGGER.info("The profile of: "+user.getName()+" has been edited");
        return user;
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
    public void createPost(Post post, boolean broadcast) throws IOException {

        String imagePathToCopy = post.getImagePath();

        Post createdPost = postLogic.create(post, tagLogic);

        if (broadcast) {
            Request request = new Request("registerpost", "RegisterPost");

            PostTransferObject postTransferObject = new PostTransferObject(createdPost, imagePathToCopy);

            request.append(postTransferObject, "post");

            initCommunicationThread(request);
        }
        
        LOGGER.info("The user: "+post.getUser().getName()+" created a new post");
    }

    @Override
    public void createPost(Post post, Tag tag, boolean broadcast) throws IOException {

        String imagePathToCopy = post.getImagePath();

        Post createdPost = postLogic.create(post, tagLogic);

        if (broadcast) {
            Request request = new Request("registerpost", "RegisterPost");

            PostTransferObject postTransferObject = new PostTransferObject(createdPost, imagePathToCopy);

            request.append(postTransferObject, "post");
            request.append(tag, "tag");

            initCommunicationThread(request);
        }
    }

    @Override
    public void createPost(Post post) throws IOException {
        postLogic.create(post, tagLogic);
    }

    @Override
    public void setUserLogged(User user) {
        this.userLogged = user;
    }

    @Override
    public User getUserLogged() {
        return this.userLogged;
    }

    /**
     * Creates and initiates a communication thread to send the given request
     * throught this business logic communication handler.
     *
     * @param request Request to send in the thread.
     */
    public void initCommunicationThread(Request request) {

        CommunicationThread requestThread = new CommunicationThread(request, communication);

        Thread thread = new Thread(requestThread);

        thread.start();
    }

    @Override
    public void subscribePostNotifications(IObserver postObserver) {
        ((IPostNotifier) communication).addPostObserver(postObserver);
    }

    @Override
    public void unsbuscribePostNotifications(IObserver postObserver) {
        ((IPostNotifier) communication).removePostObserver(postObserver);
    }

    @Override
    public void subscribeCommentNotification(IObserver commObserver) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void unsubscribeCommentNotification(IObserver commObserver) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
    @Override
    public void sendNotification(Notification notification, String provider) {
        notificationLogic.sendNotification(notification, provider);
    }
    
    @Override
    public List<Log> getAllLogs(){
        return logsLogic.getlogs();
    }
    
    @Override
    public Logger getLog(){
        return LOGGER;
    }
}
