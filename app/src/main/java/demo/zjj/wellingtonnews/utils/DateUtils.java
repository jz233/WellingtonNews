package demo.zjj.wellingtonnews.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by DouJ on 9/8/2015.
 */
public class DateUtils {
    public static String longToDateString(long time){
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        String dateString = formatter.format(date);
        return dateString;
    }
}
