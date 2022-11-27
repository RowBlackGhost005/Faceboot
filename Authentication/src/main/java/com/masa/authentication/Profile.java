package com.masa.authentication;

/**
 *
 * @author Andrea
 */
public class Profile {
    private String email;
    private String name;
    private String picture;

    public Profile() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Profile{" + "email=" + email + ", name=" + name + ", picture=" + picture + '}';
    }
    
    
    
}
