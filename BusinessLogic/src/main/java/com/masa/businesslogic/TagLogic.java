package com.masa.businesslogic;

import com.masa.domain.Tag;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.util.List;

public class TagLogic {

    private IPersistency persistency;

    public TagLogic() {
        persistency = new Persistency();
    }

    public void create(Tag tag) {
        String tags[] = tag.getName().split(" ");
        
        for (String tagName : tags) {
            Tag existingTag = persistency.getTagByName(tagName);
            if (existingTag != null) {
                System.out.println(tag);
                tag.setUsesCount(tag.getUsesCount() + 1);
                System.out.println(tag);
                persistency.editTag(tag);
            } else {
                persistency.createTag(new Tag(null, tagName));
            }
        }

    }
    
    public Tag mirrorTag(Tag tag){
        return persistency.mirrorTag(tag);
    } 

    public Tag get(String tagId) {
        return persistency.getTag(tagId);
    }

    public void edit(Tag tag) {
        persistency.editTag(tag);
    }
    
    public Tag getByName(String name) {
        return persistency.getTagByName(name);
    }
    
    public List<Tag> getAll() {
        return persistency.getAllTags();
    }

}