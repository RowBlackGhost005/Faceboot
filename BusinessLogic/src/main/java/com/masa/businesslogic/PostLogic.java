package com.masa.businesslogic;

import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.RelPostUser;
import com.masa.domain.Tag;
import com.masa.domain.User;
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
import java.util.UUID;

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
    
    public Post get(String id){
        
        Post postBd = persistency.getPost(id);

        List<Post> posts = new ArrayList<>();
        
        posts.add(postBd);
        
        for (Post post : posts) {
            List<Tag> tagsIdList = persistency.getTagsByPost(post.getId());
            ArrayList<Tag> tagsList = new ArrayList<>();
            for (Tag tag : tagsIdList) {
                Tag newTag = persistency.getTag(tag.getId());
                tagsList.add(newTag);
            }
            post.setTags(tagsList);

            List<User> usersIdList = persistency.getUsersTagged(post.getId());
            ArrayList<User> usersList = new ArrayList<>();
            for (User user : usersIdList) {
                User newUser = new User(user.getId(), persistency.getUser(user.getId()).getName(), null, null);
                usersList.add(newUser);
            }
            post.setUsers(usersList);
            post.getUser().setName(persistency.getUser(post.getUser().getId()).getName());
        }

        return posts.get(0);
    }

    public List<Post> getAllPost() {
        List<Post> posts = persistency.getAllPost();

        for (Post post : posts) {
            List<Tag> tagsIdList = persistency.getTagsByPost(post.getId());
            ArrayList<Tag> tagsList = new ArrayList<>();
            for (Tag tag : tagsIdList) {
                Tag newTag = persistency.getTag(tag.getId());
                tagsList.add(newTag);
            }
            post.setTags(tagsList);

            List<User> usersIdList = persistency.getUsersTagged(post.getId());
            ArrayList<User> usersList = new ArrayList<>();
            for (User user : usersIdList) {
                User newUser = new User(user.getId(), persistency.getUser(user.getId()).getName(), null, null);
                usersList.add(newUser);
            }
            post.setUsers(usersList);
            post.getUser().setName(persistency.getUser(post.getUser().getId()).getName());
        }

        return posts;
    }

    public Post create(Post post, TagLogic tagLogic) throws IOException {

        String savingPath = null;

        if (post.getImagePath() != null) {
            if (!post.getImagePath().contains("postsImg")) {

                String imagePath = post.getImagePath();
                String extension = imagePath.substring(imagePath.length() - 3);
                File image = new File(imagePath);
                BufferedImage bImage = ImageIO.read(image);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
//            String currentTimeStamp = dateFormat.format(new Date());

                if (extension.equalsIgnoreCase("jpg")) {
                    savingPath = "./resources/postsImg/" + UUID.randomUUID() + ".jpg";
                } else if (extension.equalsIgnoreCase("png")) {
                    savingPath = "./resources/postsImg/" + UUID.randomUUID() + ".png";
                }

                File outputFile = new File(savingPath);

                System.out.println(outputFile.getCanonicalFile());

                outputFile.createNewFile();

                ImageIO.write(bImage, extension, outputFile);

                post.setImagePath(savingPath);
            }
        }

        ArrayList<Tag> tagsList = new ArrayList<>();

        tagsList = new ArrayList<>();
        if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {

                if (tag.getId() != null) {
                    Tag tagCompare = tagLogic.get(tag.getId());

                    if (tagCompare != null) {

                        tagCompare.setUsesCount(tagCompare.getUsesCount() + 1);
                        tagLogic.edit(tagCompare);
                        tagsList.add(tagCompare);

                    } else {
                        tag.setUsesCount(1L);
                        tagLogic.mirrorTag(tag);
                        tagsList.add(tag);
                    }

                } else {
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
        }

//        if(post.getUser() == null && post.getUserId() != null){
//            post.setUser(persistency.getUser(post.getUser().getId()));
//        }
//        if(post.getId() != "" || post.getId() == null){
//            Post newPost = persistency.createPost(post);
//        }
        Post newPost = null;

        if (post.getId() != null) {
            newPost = persistency.mirrorPost(post);
        } else {
            newPost = persistency.createPost(post);
        }

        newPost.setTags(tagsList);
        newPost.setUsers(post.getUsers());
        newPost.setUser(post.getUser());

        for (Tag tag : tagsList) {
            RelPostTag relPostTag = new RelPostTag(newPost.getId(), tag.getId());
            persistency.createRelPostTag(relPostTag);
        }

        //TODO notifications
        if (post.getUsers() != null) {
            for (User user : post.getUsers()) {
                RelPostUser relPostUser = new RelPostUser(newPost.getId(), user.getId());
                persistency.createRelPostUser(relPostUser);
            }
        }
        // newPost.setTags(tagsList);
        return newPost;
    }

    public List<Post> getByTag(Tag tag){
        
        return persistency.getPostByTag(tag);
    }
}
