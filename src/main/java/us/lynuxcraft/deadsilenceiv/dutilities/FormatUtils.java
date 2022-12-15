package us.lynuxcraft.deadsilenceiv.dutilities;

import java.text.DecimalFormat;

public class FormatUtils {

    private static DecimalFormat decimalFormat;

    private static DecimalFormat getDecimalFormat(){
        if(decimalFormat == null){
            decimalFormat = new DecimalFormat("#.##");
        }
        return decimalFormat;
    }

    public static String format(double number){
        return getDecimalFormat().format(number).replaceAll(",",".");
    }

}
