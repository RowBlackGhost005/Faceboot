package com.masa.businesslogic;

import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.Tag;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

public class PostLogic {

    private IPersistency persistency;

    public PostLogic() {
        persistency = new Persistency();
    }

    public void create(Post post, TagLogic tagLogic) throws IOException {
        String savingPath = null;
        if (post.getImagePath() != null) {
            String imagePath = post.getImagePath();
            String extension = imagePath.substring(imagePath.length() - 3);
            File image = new File(imagePath);
            BufferedImage bImage = ImageIO.read(image);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            String currentTimeStamp = dateFormat.format(new Date());
            if (extension.equalsIgnoreCase("jpg")) {
                savingPath = "./src/main/resources/img/posts/" + currentTimeStamp + ".jpg";
            } else if (extension.equalsIgnoreCase("png")) {
                savingPath = "./src/main/resources/img/post/" + currentTimeStamp + ".png";
            }
            File outputFile = new File(savingPath);
            ImageIO.write(bImage, extension, outputFile);
            post.setImagePath(savingPath);
        }

        //Notificaciones
        if (post.getUsers() != null) {

        }

        List<Tag> tagsList = new ArrayList<>();
        if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {
                Tag existingTag = tagLogic.getByName(tag.getName());
                if (existingTag != null) {
                    existingTag.setUsesCount(existingTag.getUsesCount() + 1);
                    tagLogic.edit(existingTag);
                    tagsList.add(existingTag);
                } else {
                    tag.setUsesCount(1L);
                    tagLogic.create(tag);
                    existingTag = tagLogic.getByName(tag.getName());
                    if (existingTag != null) {
                        tagsList.add(existingTag);
                    }
                }
            }
        }

        Post newPost = persistency.createPost(post);

        for (Tag tag : tagsList) {
            RelPostTag relPostTag = new RelPostTag(newPost.getId(), tag.getId());
            persistency.createRelPostTag(relPostTag);
        }
    }

//    public Post get(String postId) {
//        return persistency.getPost(postId);
//    }
}
