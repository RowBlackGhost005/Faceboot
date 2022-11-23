package com.masa.domain;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Andrea
 */
public class CommentTransferObject extends PostTransferObject{

    public CommentTransferObject(String message, Icon image, ArrayList<User> users, ArrayList<Tag> tags, ArrayList<CommentTransferObject> comments, User user, String dateTime) {
        super(null, null);
    }


}