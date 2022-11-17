package com.masa.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ArrayList;
import javax.swing.Icon;

public class PostTransferObject {

    private String id;
    private String message;
    private Icon image;
    private String userId;
    private ArrayList<User> users;
    private ArrayList<Tag> tags;
    private ArrayList<CommentTransferObject> comments;
    private User user;
    private String dateTime;

    public PostTransferObject() {

    }

    public PostTransferObject(String message, Icon image, ArrayList<User> users, ArrayList<Tag> tags, ArrayList<CommentTransferObject> comments, User user, String dateTime) {
        this.message = message;
        this.image = image;
        this.users = users;
        this.tags = tags;
        this.comments = comments;
        this.user = user;
        this.dateTime = dateTime;
    }

    public PostTransferObject(String id) {
        this.id = id;
    }

    public PostTransferObject(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public PostTransferObject(String id, String message, Icon image) {
        this.id = id;
        this.message = message;
        this.image = image;
    }

    public PostTransferObject(String id, String message, Icon image, String userId) {
        this.id = id;
        this.message = message;
        this.image = image;
        this.userId = userId;
    }

    public void convertPost(Post post) {
        this.message = post.getMessage();

        //Transform BufferedImage into ICON
//        BufferedImage bi = new BufferedImage(
//                icon.getIconWidth(),
//                icon.getIconHeight(),
//                BufferedImage.TYPE_INT_RGB);
//        Graphics g = bi.createGraphics();
//// paint the Icon to the BufferedImage.
//        icon.paintIcon(null, g, 0, 0);
//        g.dispose();
        
        this.image = null;
        
        this.users = post.getUsers();
        this.tags = post.getTags();
        this.comments = null;
        this.user = post.getUser();
        if(post.getDateTime() != null){
            this.dateTime = post.getDateTime().toString();
        }
        
    }
    
    public Post revertPost(){
        Post post = new Post();
        
        post.setUser(new User("Some User"));
        post.setComments(null);
        post.setDateTime(LocalDateTime.now());
        post.setTags(null);
        post.setUsers(null);
        post.setId(null);
        post.setMessage(message);
        post.setImage(null);
        
        return post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        return Objects.equals(this.id, other.getId());
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", message=" + message + '}';
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<CommentTransferObject> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentTransferObject> comments) {
        this.comments = comments;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
