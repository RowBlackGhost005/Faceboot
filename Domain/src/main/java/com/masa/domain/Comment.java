/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.domain;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Andrea
 */
public class Comment extends Post{

    public Comment(String message, BufferedImage image, ArrayList<User> users, ArrayList<Tag> tags, ArrayList<Comment> comments, User user) {
        super(message, image, users, tags, comments, user);
    }


}