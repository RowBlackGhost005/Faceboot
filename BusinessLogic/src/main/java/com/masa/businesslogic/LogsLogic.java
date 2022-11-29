/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.businesslogic;

import com.masa.domain.Log;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class LogsLogic {
     private IPersistency persistency;

    public LogsLogic() {
        this.persistency = new Persistency();
    }
    
    public List<Log> getlogs(){
       return persistency.getAllLogs();
    }
     
}
