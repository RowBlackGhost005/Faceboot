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

public class Persistency implements IPersistency {

    private DAOFactory daoFactory;
    private DAOUsers users;
    private DAOPosts posts;
    private DAOTags tags;
    private DAORelPostTag postTags;
    private DAORelPostUser postUsers;
    private DAOLogs logs;
    private DAONotifications notifications;
    
    private DAOComments comments;

    public Persistency() {
        daoFactory = new DAOFactory();
        users = daoFactory.createDAOUsers();
        posts = daoFactory.createDAOPosts();
        tags = daoFactory.createDAOTags();
        postTags = daoFactory.createDAORelPostTag();
        postUsers=daoFactory.createDAORelPostUser();
        logs = daoFactory.createDAOLogs();
        notifications = daoFactory.createDAONotifications();
        comments=daoFactory.createDAOComments();

    }

    @Override
    public User createUser(User user) {
        return users.create(user);
    }

    @Override
    public void editUser(User user) {
        users.edit(user);
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return users.getAll();
    }

    @Override
    public User getUserLogin(User user) throws SQLException {
        return users.getByLogin(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.getByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return users.getByPhone(phone);
    }

    @Override
    public Post createPost(Post post) {
        return posts.create(post);
    }

    @Override
    public Post editPost(Post post){
        return posts.edit(post);
    }
    @Override
    public Post getPost(String postId) {
        return posts.get(postId);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tags.create(tag);
    }

    @Override
    public Tag getTag(String tagId) {
        return tags.get(tagId);
    }

    @Override
    public Tag editTag(Tag tag) {
        return tags.edit(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tags.getAll();
    }

    @Override
    public Tag getTagByName(String name) {
        return tags.getByName(name);
    }

    @Override
    public Boolean createRelPostTag(RelPostTag relPostTag) {
        return postTags.create(relPostTag);
    }

    @Override
    public RelPostTag getRelPostTag(String relPostTagId) {
        return postTags.get(relPostTagId);
    }

    @Override
    public RelPostTag getRelPostTag(String postId, String tagId) {
        return postTags.getPostTag(postId, tagId);
    }
    
    @Override
    public List<Tag> getTagsByPost(String postId){
        
         return  postTags.getTagsByPost(postId);
    }

    @Override
    public List<Post> getAllPost() {
        return posts.getAll();
    }

    @Override
    public List<Log> getAllLogs() {
        return logs.getAll();
    }

    @Override
    public Boolean createRelPostUser(RelPostUser relPostUser) {
        return postUsers.create(relPostUser);
    }

    @Override
    public List<User> getUsersTagged(String postId) {
        return postUsers.getUsersTagged(postId);
    }

    @Override
    public Post mirrorPost(Post post) {
        return posts.mirrorPost(post);
    }

    @Override
    public Tag mirrorTag(Tag tag) {
        return tags.mirrorTag(tag);
    }

    @Override
    public User mirrorUser(User user) {
        return users.mirrorUser(user);
    }
    
    @Override
    public Comment createComment(Comment comment){
        return comments.create(comment);
    }
    
    @Override
    public List<Comment> getCommentsByPost(String idPost){
        return comments.getByPost(idPost);
    }

    @Override
    public List<Post> getPostByTag(Tag tag) {
        return posts.getByTag(tag);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notifications.create(notification);
    }

    @Override
    public Notification getNotification(String notificaitonId) {
        return notifications.get(notificaitonId);
    }

    @Override
    public List<Notification> getNotificationsByUser(String userId) {
        return notifications.getByUser(userId);
    }

    @Override
    public Notification createNotificationMirror(Notification notification) {
        return notifications.mirror(notification);
    }

}
