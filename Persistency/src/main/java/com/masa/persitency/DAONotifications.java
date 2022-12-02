package com.masa.persitency;

import com.masa.domain.Notification;
import com.masa.domain.RelPostTag;
import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Marin
 */
public class DAONotifications {

    private IConnection connectionDB;

    public DAONotifications(IConnection connection) {

        this.connectionDB = connection;
    }

    public Notification create(Notification notification) {

        UUID idGen = UUIDGenerator.getNewId();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO notifications (id , sender , receiver , message , type , date) VALUES ('%s', '%s' , '%s' , '%s', '%s' , '%s');",
                    idGen,
                    notification.getFrom().getId(),
                    notification.getTo().getId(),
                    notification.getMessage(),
                    notification.getType(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(notification.getDate()));

            statement.executeUpdate(query);

            connection.close();

            Notification notificationBd = get(idGen.toString());

            return notificationBd;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public Notification mirror(Notification notification) {
        
        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO notifications (id , sender , receiver , message , type , date) VALUES ('%s', '%s' , '%s' , '%s', '%s' , '%s');",
                    notification.getId(),
                    notification.getFrom().getId(),
                    notification.getTo().getId(),
                    notification.getMessage(),
                    notification.getType(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(notification.getDate()));

            statement.executeUpdate(query);

            connection.close();

            return notification;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return notification;
        }
    }
    
    public Notification get(String notificaitonId) {
        RelPostTag relPostTag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id , sender , receiver , message , type , date FROM notifications WHERE id = '%s';",
                    notificaitonId);

            ResultSet result = statement.executeQuery(query);

            String id = result.getString("id");
            String sender = result.getString("sender");
            String receiver = result.getString("receiver");
            String message = result.getString("message");
            String type = result.getString("type");
            Timestamp date = result.getTimestamp("date");

            Date dateNot = new Date(date.getTime());

            connection.close();

            Notification notification = new Notification(id, new User(receiver), new User(sender), message, type, dateNot);

            return notification;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Notification> getByUser(String userId) {
        List<Notification> notifications = new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id , sender , receiver , message , type , date FROM notifications WHERE sender = '%s';",
                    userId);

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {

                String id = result.getString("id");
                String sender = result.getString("sender");
                String receiver = result.getString("receiver");
                String message = result.getString("message");
                String type = result.getString("type");

                Timestamp date = result.getTimestamp("date");

                Date dateNot = new Date(date.getTime());

                Notification bdNotification = new Notification(id, new User(receiver), new User(sender), message, type, date);

                notifications.add(bdNotification);
            }

            connection.close();
            return notifications;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return notifications;
        }
    }
}
