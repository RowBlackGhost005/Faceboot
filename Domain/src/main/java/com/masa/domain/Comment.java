package com.masa.domain;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;

/**
 *
 * @author Andrea
 */
public class Comment {
    private String idPost;
    private String id;
    private User user;
    private Date dateTime;
    private String message;

    public Comment(User user, Date dateTime, String message, String idPost) {
        this.user = user;
        this.dateTime = dateTime;
        this.message = message;
        this.idPost=idPost;
    }

    public Comment(String id, User user, Date dateTime, String message, String idPost) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.message = message;
        this.idPost=idPost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
    

}