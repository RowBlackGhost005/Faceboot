package com.masa.persitency;

public class DAOFactory {

    IConnection bdConnection;

    public DAOFactory() {
        bdConnection = new Connection();
    }

    public DAOUsers createDAOUsers() {
        return new DAOUsers(bdConnection);
    }

    public DAOPosts createDAOPosts() {
        return new DAOPosts(bdConnection);
    }

    public DAOTags createDAOTags() {
        return new DAOTags(bdConnection);
    }

    public DAORelPostTag createDAORelPostTag() {
        return new DAORelPostTag(bdConnection);
    }
    
     public DAORelPostUser createDAORelPostUser() {
        return new DAORelPostUser(bdConnection);
    }
    
    public DAOLogs createDAOLogs(){
        return new DAOLogs();
    }
    
    public DAONotifications createDAONotifications(){
        return new DAONotifications(bdConnection);
    }

    DAOComments createDAOComments() {
        return new DAOComments(bdConnection);
    }

}
