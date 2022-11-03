package com.masa.domain;

import java.util.Date;

/**
 * Class that holds the information about an user.
 * 
 * @author Luis Angel Marin
 */
public class User {
    
    private int id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String birthDate;
    private int age;

    public User() {
    }

    public User(String name, String email, String phone, String gender, String birthDate, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
    }

    
    
    public User(int id, String name, String email, String phone, String gender, String birthDate, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final User other = (User) obj;
        return this.id == other.id;
    }
    
    
}
