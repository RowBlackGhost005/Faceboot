package com.masa.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Tag {

    private String id;
    private String name;
    private Long usesCount;

    public Tag() {
        this.usesCount = 1L;
    }

    public Tag(String id) {
        this.id = id;
        this.usesCount = 1L;
    }

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
        this.usesCount = 1L;
    }

    public Tag(String id, String name, Long usesCount) {
        this.id = id;
        this.name = name;
        this.usesCount = usesCount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUsesCount() {
        return usesCount;
    }

    public void setUsesCount(Long usesCount) {
        this.usesCount = usesCount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Tag other = (Tag) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name=" + name + ", usesCount=" + usesCount + '}';
    }

}
