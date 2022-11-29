/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.domain;

import java.time.LocalDateTime;

/**
 *
 * @author Andrea
 */
public class RelPostUser {
      private Long id;
    private String postId;
    private String userId;
    private LocalDateTime creationDateTime;

    public RelPostUser(Long id, String postId, String userId, LocalDateTime creationDateTime) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.creationDateTime = creationDateTime;
    }

    public RelPostUser(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }

    
    public RelPostUser(Long id, String postId, String userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
    
    
}
