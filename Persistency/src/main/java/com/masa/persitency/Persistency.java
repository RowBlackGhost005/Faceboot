package com.masa.persitency;

import com.masa.domain.Post;
import com.masa.domain.RelPostTag;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.sql.SQLException;
import java.util.List;

public class Persistency implements IPersistency {

    private DAOFactory daoFactory;
    private DAOUsers users;
    private DAOPosts posts;
    private DAOTags tags;
    private DAORelPostTag postTags;

    public Persistency() {
        daoFactory = new DAOFactory();
        users = daoFactory.createDAOUsers();
        posts = daoFactory.createDAOPosts();
        tags = daoFactory.createDAOTags();
        postTags = daoFactory.createDAORelPostTag();

    }

    @Override
    public void createUser(User user) {
        users.create(user);
    }

    @Override
    public void editUser(User user) {
        users.edit(user);
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return users.getAll();
    }

    @Override
    public User getUserLogin(User user) throws SQLException {
        return users.getByLogin(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.getByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return users.getByPhone(phone);
    }

    @Override
    public Post createPost(Post post) {
        return posts.create(post);
    }

    @Override
    public Post getPost(String postId) {
        return posts.get(postId);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tags.create(tag);
    }

    @Override
    public Tag getTag(String tagId) {
        return tags.get(tagId);
    }

    @Override
    public Tag editTag(Tag tag) {
        return tags.edit(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tags.getAll();
    }

    @Override
    public Tag getTagByName(String name) {
        return tags.getByName(name);
    }

    @Override
    public Boolean createRelPostTag(RelPostTag relPostTag) {
        return postTags.create(relPostTag);
    }

    @Override
    public RelPostTag getRelPostTag(String relPostTagId) {
        return postTags.get(relPostTagId);
    }

    @Override
    public RelPostTag getRelPostTag(String postId, String tagId) {
        return postTags.getPostTag(postId, tagId);
    }

    @Override
    public List<Post> getAllPost() {
        return posts.getAll();
    }

}
