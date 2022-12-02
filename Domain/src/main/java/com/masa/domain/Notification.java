package com.masa.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jjavi
 */
public class Notification {
    
    private String id;
    private User to;
    private User from;
    private String message;
    private String type;
    private Date date;

    public Notification() {
    }

    public Notification(String id) {
        this.id = id;
    }

    public Notification(User to, User from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }

    public Notification(String id, User to, User from, String message) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
    }

    public Notification(String id, User to, User from, String message, String type) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
        this.type = type;
    }

    public Notification(String id, User to, User from, String message, String type, Date date) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
        this.type = type;
        this.date = date;
    }
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User receptor) {
        this.to = receptor;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User emisor) {
        this.from = emisor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Notification other = (Notification) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", receptor=" + to + ", emisor=" + from + ", message=" + message + '}';
    }
}
