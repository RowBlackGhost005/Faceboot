package com.masa.persitency;

import com.masa.domain.RelPostTag;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORelPostTag {

    private IConnection connectionDB;

    public DAORelPostTag(IConnection connection) {
        this.connectionDB = connection;
    }

    public boolean create(RelPostTag relPostTag) {

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO relPostTag (id_post, id_tag) VALUES ('%s', '%s');",
                    relPostTag.getPostId(),
                    relPostTag.getTagId());

            int registries = statement.executeUpdate(query);

            connection.close();

            return registries == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public RelPostTag get(String relPostTagId) {
        RelPostTag relPostTag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, id_post, id_tag FROM relPostTag WHERE id = '%s';",
                    relPostTagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                Long id = result.getLong("id");
                String postId = result.getString("id_post");
                String tagId = result.getString("id_tag");
                relPostTag = new RelPostTag(id, postId, tagId);
            }

            connection.close();
            return relPostTag;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return relPostTag;
        }
    }
    
       public List<Tag> getTagsByPost(String postId) {
        List<Tag> tags=new ArrayList<>();

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id_tag FROM relPostTag WHERE id_post = '%s';",
                    postId);
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
 
                String tagId = result.getString("id_tag");
                Tag tag = new Tag(tagId);
                tags.add(tag);
            }

            connection.close();
            return tags;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return tags;
        }
    }

    public RelPostTag getPostTag(String postId, String tagId) {
        RelPostTag relPostTag = null;

        try {
            java.sql.Connection connection = this.connectionDB.connectionDB();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT id, id_post, id_tag FROM relPostTag WHERE id_post = '%s' AND id_tag = '%s';",
                    postId, tagId);
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                Long id = result.getLong("id");
                String existingPostId = result.getString("idPost");
                String existingTagId = result.getString("idTag");
                relPostTag = new RelPostTag(id, existingPostId, existingTagId);
            }

            connection.close();
            return relPostTag;

        } catch (SQLException e) {
            Logger.getLogger(DAORelPostTag.class.getName()).log(Level.SEVERE, null, e);
            return relPostTag;
        }
    }

}
