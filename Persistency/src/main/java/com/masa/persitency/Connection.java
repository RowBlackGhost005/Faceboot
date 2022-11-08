package com.masa.persitency;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection implements IConnection{

    @Override
    public java.sql.Connection getConnection(){
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");    
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public java.sql.Connection connectionDB() {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");    
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
