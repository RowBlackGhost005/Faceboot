package persistency;

import interfaces.IConnection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection implements IConnection{

    @Override
    public java.sql.Connection createConnection() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        return connection;    
    }
    
}
