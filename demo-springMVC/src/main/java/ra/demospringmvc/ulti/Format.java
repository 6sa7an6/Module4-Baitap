package ra.demospringmvc.ulti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Format {
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}
