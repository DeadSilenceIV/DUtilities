package us.lynuxcraft.deadsilenceiv.dutilities;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FormatUtils {

    private static DecimalFormat decimalFormat;

    private static SimpleDateFormat dateFormat;

    private static SimpleDateFormat hourFormat;

    public static String format(double number){
        return getDecimalFormat().format(number).replaceAll(",",".");
    }

    public static String formatDate(Timestamp timestamp){
        return getDateFormat().format(timestamp);
    }

    public static String formatHour(Timestamp timestamp){
        return getHourFormat().format(timestamp);
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

    private static SimpleDateFormat getHourFormat(){
        if(hourFormat == null){
            hourFormat = new SimpleDateFormat("HH-mm-ss");
        }
        return hourFormat;
    }
}
