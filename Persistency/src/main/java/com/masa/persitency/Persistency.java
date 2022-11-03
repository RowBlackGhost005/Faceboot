package com.masa.persitency;

import com.masa.domain.User;

/**
 *
 * @author Luis Angel Marin
 */
public class Persistency implements IPersistency{

    private DAOUsers users;
    
    public Persistency(){
        this.users = new DAOUsers();
    }
    
    @Override
    public User createUser(User user) {
        users.create(user);
        return user;
    }
    
}
