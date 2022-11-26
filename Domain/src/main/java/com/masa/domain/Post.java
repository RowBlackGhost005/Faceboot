package com.masa.domain;

import java.util.Objects;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    private String id;
    private String message;
    private BufferedImage image;
    private String imagePath;
    private String userId;
    private ArrayList<User> users;
    private ArrayList<Tag> tags;
    private ArrayList<Comment> comments;
    private User user;

    public Post() {
    }

    public Post(String id) {
        this.id = id;
    }

    public Post(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public Post(String id, String message, BufferedImage image) {
        this.id = id;
        this.message = message;
        this.image = image;
    }

    public Post(String id, String message, String imagePath) {
        this.id = id;
        this.message = message;
        this.imagePath = imagePath;
    }

    public Post(String id, String message, BufferedImage image, String userId) {
        this.id = id;
        this.message = message;
        this.image = image;
        this.userId = userId;
    }

    public Post(String message, BufferedImage image, ArrayList<User> users, ArrayList<Tag> tags, ArrayList<Comment> comments, User user) {
        this.message = message;
        this.image = image;
        this.users = users;
        this.tags = tags;
        this.comments = comments;
        this.user = user;
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", message=" + message + '}';
    }

}
