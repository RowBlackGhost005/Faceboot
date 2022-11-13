package com.masa.domain;

import java.awt.image.BufferedImage;
import java.util.Objects;

import java.awt.image.BufferedImage;

public class Post {

    private String id;
    private String message;
    private BufferedImage image;
    private String userId;

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

    public Post(String id, String message, BufferedImage image, String userId) {
        this.id = id;
        this.message = message;
        this.image = image;
        this.userId = userId;
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
