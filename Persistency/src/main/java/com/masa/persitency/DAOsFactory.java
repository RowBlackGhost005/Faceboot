package com.masa.persitency;

public class DAOsFactory {

    IConnection bdConnection;

    public DAOsFactory() {
        bdConnection = new Connection();
    }

    public DAOUsers createDAOUsers() {
        return new DAOUsers(bdConnection);
    }

}
