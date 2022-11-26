package com.masa.persitency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeGenerator {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime toLocalDateTime(String str) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

}
