package com.masa.businesslogic;

import com.masa.communication.Communication;
import com.masa.domain.Post;
import com.masa.domain.User;
import domain.Request;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class BusinessLogic implements IBusinessLogic {

    private UserLogic userLogic;
    private PostLogic postLogic;
    private TagLogic tagLogic;

    private Communication communication;

    public BusinessLogic() {
        this.userLogic = new UserLogic();
        this.postLogic = new PostLogic();
        this.tagLogic = new TagLogic();
        this.communication = new Communication("Peer", this);
    }

    @Override
    public User login(User user) throws Exception {
        return userLogic.login(user);
    }

    @Override
    public User registerUser(User user, boolean broadcast) throws Exception {

        user = userLogic.registerUser(user);

        if (broadcast) {
            Request request = new Request("registeruser", "RegisterUser");

            request.append(user, "user");

            communication.sendToAllPeers(request);
        }

        return user;
    }

    @Override
    public User editUser(User user, boolean broadcast) throws Exception {

        user = userLogic.editUser(user);

        if (broadcast) {
            Request request = new Request("edituser", "editUser");

            request.append(user, "user");

            communication.sendToAllPeers(request);
        }

        return user;
    }

    @Override
    public User getUser(String userId) {
        return userLogic.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userLogic.getAll();
    }

    @Override
    public void createPost(Post post) throws IOException {
        postLogic.create(post, tagLogic);
    }

}
