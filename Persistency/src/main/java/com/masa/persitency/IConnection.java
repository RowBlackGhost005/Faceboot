package com.masa.persitency;

import java.sql.Connection;

/**
 *
 * @author Luis Angel Marin
 */
public interface IConnection {
    
    public Object getConnection();

    public Connection connectionDB();
}
