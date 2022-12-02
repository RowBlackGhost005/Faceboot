package com.masa.businesslogic;

import com.masa.authentication.Google;
import com.masa.communication.CommHandler;
import com.masa.communication.ICommHandler;
import com.masa.domain.Comment;
import com.masa.domain.Log;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.PostTransferObject;
import com.masa.domain.Request;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import com.masa.utils.IOnlineUserNotifier;
import com.masa.utils.IOnlineUserObserver;
import com.masa.utils.IPostNotifier;
import com.masa.utils.IPostObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private CommentLogic commentsLogic;

    private User userLogged;
    private ICommHandler communication;

    public BusinessLogic() {
        LOGGER = LogManager.getLogger(BusinessLogic.class.getName());
        this.userLogic = new UserLogic();
        this.postLogic = new PostLogic();
        this.tagLogic = new TagLogic();
        this.notificationLogic = new NotificationLogic();
        this.logsLogic = new LogsLogic();
        this.commentsLogic = new CommentLogic();
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
    public User registerUser(User user, boolean broadcast) throws Exception {

        User registedUser = null;

        try {
            registedUser = userLogic.registerUser(user);
            notificationLogic.registerUser(user.getPhone());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        if (broadcast) {
            Request request = new Request("registeruser", "RegisterUser");

            request.append(registedUser, "user");

            initCommunicationThread(request);

        }
        LOGGER.info("The user: " + user.getName() + " has succesfuly Signed Up");

        return registedUser;
    }

    @Override
    public User registerExternalUser(User user, boolean broadcast) {

        User registeredUser = null;

        try {
            registeredUser = userLogic.registerExternalUser(user);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        if (broadcast) {
            Request request = new Request("registerexternalusers", "RegisterExternalUser");

            request.append(registeredUser, "user");

            initCommunicationThread(request);
        }

        LOGGER.info("The user: " + registeredUser.getName() + " has succesfuly Signed Up with Google");
        return registeredUser;
    }

    @Override
    public User login(User user, String method) throws Exception {

        User userLogged = userLogic.login(user, method);

//        if(method != "local" && userLogged.getId() != null){
        setUserLogged(userLogged);
//        }
        if (userLogged != null) {
            LOGGER.info("The user: " + userLogged.getName() + " has Logged in");
        }

        return userLogged;
    }

    @Override
    public User loginWith(String method) throws Exception {
        User user;
        switch (method) {
            case "GOOGLE":

                user = userLogic.loginUser(new Google());
                LOGGER.info("The user: " + user.getName() + "has succesfuly Signed Up");
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

        LOGGER.info("The profile of: " + user.getName() + " has been edited");

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

        LOGGER.info("The user: " + post.getUser().getName() + " created a new post");
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
    public void createComment(Comment comment, boolean broadcast)throws IOException{
        Comment createdComment = commentsLogic.create(comment);
    }

    @Override
    public void createPost(Post post) throws IOException {
        postLogic.create(post, tagLogic);
    }

    @Override
    public void setUserLogged(User user) {

        this.userLogged = user;

        if (user != null && user.getId() != null) {
            Request request = new Request("addOnlineUser", "AddOnlineUser");

            request.append(user, "user");

            initCommunicationThread(request);
        }

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
    public void subscribePostNotifications(IPostObserver postObserver) {
        ((IPostNotifier) communication).addPostObserver(postObserver);
    }

    @Override
    public void unsbuscribePostNotifications(IPostObserver postObserver) {
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
        createNotification(notification, true);
    }

    @Override
    public List<Log> getAllLogs() {
        return logsLogic.getlogs();
    }

    @Override
    public Logger getLog() {
        return LOGGER;
    }

    @Override
    public void subscribeOnlineUserNotification(IOnlineUserObserver onlineUserObserver) {
        ((IOnlineUserNotifier) communication).addOnlineUserObserver(onlineUserObserver);
    }

    @Override
    public void unsubscribeOnlineUserNotification(IOnlineUserObserver onlineUserObserver) {
        ((IOnlineUserNotifier) communication).removeOnlineUserObserver(onlineUserObserver);
    }

    @Override
    public void getAllOnlineUsers() {

        Request request = new Request("getOnlineUser", "GetOnlineUser");

        initCommunicationThread(request);
    }

    @Override
    public void logout() {

        Request request = new Request("removeOnlineUser", "RemoveOnlineUser");

        request.append(userLogged, "user");

        initCommunicationThread(request);

        this.setUserLogged(null);
    }

    @Override
    public Post editPost(Post post) throws IOException{
        return postLogic.editPost(post);
    }

    public List<Post> getPostByTag(String tag) {
        Tag tagStored = tagLogic.getByName(tag);

        List<Post> postsWithTag = postLogic.getByTag(tagStored);

        ArrayList<Tag> tags = new ArrayList<Tag>();

        tags.add(tagStored);

        List<Post> posts = new ArrayList<Post>();

        for (Post buildPost : postsWithTag) {

            Post postbd = postLogic.get(buildPost.getId());

            System.out.println(postbd);

            posts.add(postbd);

//            buildPost.setTags(tags);
//            
//            User user = userLogic.get(buildPost.getId());
//            
//            buildPost.setUser(user);
        }

        return posts;
    }

    @Override
    public Notification createNotification(Notification notification, boolean broadcast) {

        Notification notificationNew = null;
        
        
        if(notification.getId() != null){
            notificationNew = notificationLogic.mirror(notification);
        }else{
            notificationNew = notificationLogic.create(notification);
        }
        
        Request request = new Request("createNotification", "CreateNotification");

        if (broadcast) {
            request.append(notificationNew, "notification");

            initCommunicationThread(request);
        }

        return notificationNew;
    }

    @Override
    public Notification getNotification(String notificaitonId) {

        Notification bdNotification = notificationLogic.get(notificaitonId);

        User userFrom = userLogic.get(bdNotification.getFrom().getId());
        User userTo = userLogic.get(bdNotification.getTo().getId());

        bdNotification.setFrom(userFrom);
        bdNotification.setTo(userTo);

        return bdNotification;
    }

    @Override
    public List<Notification> getNotificationsByUser(String userId) {

        List<Notification> bdNotifications = notificationLogic.getByUser(userId);

        List<Notification> userNotifications = new ArrayList<>();

        for (Notification notification : bdNotifications) {

            User userFrom = userLogic.get(notification.getFrom().getId());
            User userTo = userLogic.get(notification.getTo().getId());

            notification.setFrom(userFrom);
            notification.setTo(userTo);

            userNotifications.add(notification);
        }

        return userNotifications;
    }

}
