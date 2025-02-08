package us.lynuxcraft.deadsilenceiv.dutilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FormatUtils {

    private static DecimalFormat decimalFormat;

    private static SimpleDateFormat dateFormat;

    private static SimpleDateFormat completeDateFormat;

    private static SimpleDateFormat hourFormat;

    public static String format(double number){
        return getDecimalFormat().format(number).replaceAll(",",".");
    }

    public static String formatCompleteDate(Timestamp timestamp){
        return getCompleteDateFormat().format(timestamp);
    }

    public static String formatDate(Timestamp timestamp){
        return getDateFormat().format(timestamp);
    }

    public static String formatHour(Timestamp timestamp){
        return getHourFormat().format(timestamp);
    }

    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = getCompleteDateFormat();
            // you can change format of date
            Date date = formatter.parse(strDate);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String compactNumberFormat(double value) {
        String[] arr = {"", "k", "m", "b", "t", "p", "e"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        if(arr.length >= index+1) {
            return String.format("%s%s", getDecimalFormat().format(value), arr[index]);
        }else{
            return getDecimalFormat().format(value);
        }
    }

    public static String listToString(List<String> list){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            builder.append(line);
            if(i < list.size()-1){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String timeRemaining(long endTime){
        LocalDateTime currentTime = LocalDateTime.now();
        Instant future = Instant.ofEpochMilli(endTime);
        LocalDateTime futureTime = future.atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(currentTime, futureTime);
        long days = Math.max(0,duration.toDays());
        long hours = Math.max(0,duration.minusDays(days).toHours());
        long minutes = Math.max(0,duration.minusDays(days).minusHours(hours).toMinutes());
        long seconds = Math.max(0,duration.minusDays(days).minusHours(hours).minusMinutes(minutes).getSeconds());
        return String.format("%dd %dh %dm %ds", days, hours, minutes, seconds);
    }

    private static DecimalFormat getDecimalFormat(){
        if(decimalFormat == null){
            decimalFormat = new DecimalFormat("#.##");
        }
        return decimalFormat;
    }

    private static SimpleDateFormat getDateFormat(){
        if(dateFormat == null){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return dateFormat;
    }

    private static SimpleDateFormat getCompleteDateFormat(){
        if(completeDateFormat == null){
            completeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return completeDateFormat;
    }

    private static SimpleDateFormat getHourFormat(){
        if(hourFormat == null){
            hourFormat = new SimpleDateFormat("HH:mm:ss");
            hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return hourFormat;
    }
}
