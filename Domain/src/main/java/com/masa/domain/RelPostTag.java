package com.masa.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class RelPostTag {

    private Long id;
    private String postId;
    private String tagId;
    private LocalDateTime creationDateTime;

    public RelPostTag() {
    }

    public RelPostTag(String postId, String tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public RelPostTag(Long id, String postId, String tagId) {
        this.id = id;
        this.postId = postId;
        this.tagId = tagId;
    }

    public RelPostTag(Long id, String postId, String tagId, LocalDateTime creationDateTime) {
        this.id = id;
        this.postId = postId;
        this.tagId = tagId;
        this.creationDateTime = creationDateTime;
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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final RelPostTag other = (RelPostTag) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RelPostTag{" + "id=" + id + ", postId=" + postId + ", tagId=" + tagId + '}';
    }

}
