package com.masa.businesslogic;

import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.Tag;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Class that controls all the logic of a post.
 * 
 * @author Diego Ayala
 */
public class PostLogic {

    private IPersistency persistency;

    public PostLogic() {
        persistency = new Persistency();
    }

    public Post create(Post post) {
        return persistency.createPost(post);
    }

    public Post create(Post post, Tag tags, TagLogic tagLogic) {
        String[] tagsNamesList = tags.getName().split(" ");

        return null;
    }

    public List<Post> getAllPost() {
        return persistency.getAllPost();
    }

    public Post create(Post post, TagLogic tagLogic) throws IOException {

        String savingPath = null;

        if (!post.getImagePath().contains("postsImg")) {
            
            String imagePath = post.getImagePath();
            String extension = imagePath.substring(imagePath.length() - 3);
            File image = new File(imagePath);
            BufferedImage bImage = ImageIO.read(image);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            String currentTimeStamp = dateFormat.format(new Date());

            if (extension.equalsIgnoreCase("jpg")) {
                savingPath = "./resources/postsImg/" + currentTimeStamp + ".jpg";
            } else if (extension.equalsIgnoreCase("png")) {
                savingPath = "./resources/postsImg/" + currentTimeStamp + ".png";
            }

            File outputFile = new File(savingPath);

            System.out.println(outputFile.getCanonicalFile());

            outputFile.createNewFile();

            ImageIO.write(bImage, extension, outputFile);

            post.setImagePath(savingPath);
        }
        
        ArrayList<Tag> tagsList = new ArrayList<>();

        //Notificaciones
        if (post.getUsers() != null) {

        }

        tagsList = new ArrayList<>();
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
        // newPost.setTags(tagsList);
        return newPost;
    }


}
