package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeTimeIntoString {
    public static String makeCurrentDateIntoString(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return makeStringReadable(df.format(today));
    }
    public static String makeStringReadable(String s){
        String sNew = s.replaceAll("/", "_");
        return sNew.replaceAll(":","-");
    }
}
