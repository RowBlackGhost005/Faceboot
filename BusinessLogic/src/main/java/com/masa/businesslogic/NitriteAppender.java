/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.businesslogic;

import java.time.LocalDateTime;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import static org.dizitart.no2.Document.createDocument;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

/**
 * Custom Appender for Log4j using Nitrite database
 * @author Andrea
 */
@Plugin(
  name = "NitriteAppender", 
  category = Core.CATEGORY_NAME, 
  elementType = Appender.ELEMENT_TYPE)
public class NitriteAppender extends AbstractAppender{

    /**
     * Defualt constructor
     * @param name appender name
     * @param filter appender filter
     */
    public NitriteAppender(String name, Filter filter) {
        super(name, filter, null);
        
        
    }
    
    /**
     * Loggs all the message to Nitrite database
     * @param le log event
     */
    @Override
    public void append(LogEvent le) {
        Nitrite db = Nitrite.builder()
        .compressed()
        .filePath("logger.db")
        .openOrCreate("user", "password");
        
        NitriteCollection collection = db.getCollection("logs");
        Document doc = createDocument("log", le.getLoggerName())
                .put("level", le.getLevel().name())
                .put("message", le.getMessage().getFormattedMessage())
                .put("date", LocalDateTime.now());
        
        collection.insert(doc);
        db.close();
    }
    
    /**
     * creates the Nitrite appender
     * @param name appender name
     * @param filter appender filter
     * @return a nitrite appender
     */
    
    @PluginFactory
    public static NitriteAppender createAppender(
      @PluginAttribute("name") String name, 
      @PluginElement("Filter") Filter filter) {
        return new NitriteAppender(name, filter);
    }
}

