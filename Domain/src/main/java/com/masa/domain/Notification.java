/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.domain;

import java.util.Objects;

/**
 *
 * @author jjavi
 */
public class Notification {
    
    private Integer id;
    private User to;
    private User from;
    private String message;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(User to, User from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }

    public Notification(Integer id, User to, User from, String message) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
