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
    private User receptor;
    private User emisor;
    private String message;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(User receptor, User emisor, String message) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.message = message;
    }

    public Notification(Integer id, User receptor, User emisor, String message) {
        this.id = id;
        this.receptor = receptor;
        this.emisor = emisor;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getReceptor() {
        return receptor;
    }

    public void setReceptor(User receptor) {
        this.receptor = receptor;
    }

    public User getEmisor() {
        return emisor;
    }

    public void setEmisor(User emisor) {
        this.emisor = emisor;
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
        return "Notification{" + "id=" + id + ", receptor=" + receptor + ", emisor=" + emisor + ", message=" + message + '}';
    }
}
