package com.masa.persitency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Angel Marin
 */
public class SQLConnection implements IConnection{

    private static java.sql.Connection connection;

    public SQLConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
//            connection = DriverManager.getConnection("jdbc:sqlite:///C:/Users/luisg/Desktop/Faceboot/Persistency/database.db");
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public java.sql.Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:database.db");
//                connection = DriverManager.getConnection("jdbc:sqlite:///C:/Users/luisg/Desktop/Faceboot/Persistency/database.db");
            } catch (SQLException ex) {
                Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return connection;

    }

    @Override
    public Connection connectionDB() {
        return connection;
    }

}
