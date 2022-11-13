package com.masa.businesslogic;

import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.Tag;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.util.ArrayList;
import java.util.List;

public class PostLogic {

    private IPersistency persistency;

    public PostLogic() {
        persistency = new Persistency();
    }

    public void create(Post post) {
        persistency.createPost(post);
    }

    public void create(Post post, Tag tags, TagLogic tagLogic) {
        String[] tagsNamesList = tags.getName().split(" ");
        List<Tag> tagsList = new ArrayList<>();

        for (String tag : tagsNamesList) {
            Tag existingTag = tagLogic.getByName(tag);
            if (existingTag != null) {
                existingTag.setUsesCount(existingTag.getUsesCount() + 1);
                tagLogic.edit(existingTag);
                tagsList.add(existingTag);
            } else {
                tagLogic.create(new Tag(null, tag));
                existingTag = tagLogic.getByName(tag);
                if (existingTag != null) {
                    tagsList.add(existingTag);
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