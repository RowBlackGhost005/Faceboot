package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnection {

    Connection createConnection() throws SQLException;

}
