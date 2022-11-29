/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.persitency;

import com.masa.domain.Log;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

/**
 *
 * @author Andrea
 */
public class DAOLogs {
    
    public List<Log> getAll(){
        Nitrite db = Nitrite.builder()
        .compressed()
        .filePath("logger.db")
        .openOrCreate("user", "password");
        
        NitriteCollection collection = db.getCollection("logs");
        Cursor docResults = collection.find();
        
        List<Log> logs = new ArrayList<>();
        for(Document document:docResults){
            String level = document.get("level").toString();
            String message =document.get("message").toString();
            String date = document.get("date").toString();
            logs.add(new Log(level,message,date));
        }
        db.close();
        return logs;
    }
}
