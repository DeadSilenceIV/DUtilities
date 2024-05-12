package us.lynuxcraft.deadsilenceiv.dutilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static String compactNumberFormat(float value) {
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
            hourFormat = new SimpleDateFormat("HH-mm-ss");
        }
        return hourFormat;
    }
}
