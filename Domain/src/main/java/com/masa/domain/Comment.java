package com.masa.domain;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;

/**
 *
 * @author Andrea
 */
public class Comment extends Post{

    public Comment(String message, ArrayList<User> users, ArrayList<Tag> tags, ArrayList<Comment> comments, User user, Date dateTime) {
        super(message, users, tags, comments, user, dateTime);
    }


}