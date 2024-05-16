package vn.kma.hrmactvn.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommonUtils {


    private static <T> boolean isNullOrEmpty(T t) {
        return t == null || t.toString().isEmpty();
    }

    public static String convertDateFromDomainToString(String date) {
        if (date == null || date.isEmpty()) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Timestamp convertStringToTimeStamp(String date) {
        if (isNullOrEmpty(date)) {
            return null;
        }
        try {
            return Timestamp.valueOf(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        } catch (Exception e) {
            log.error("Error when convert string to timestamp: {}", e.getMessage());
        }
        return null;
    }


    public static String convertDateFromDomainToString(Timestamp date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String getGender(int gender) {
        return gender == 1 ? "Nam" : "Nữ";
    }



}
