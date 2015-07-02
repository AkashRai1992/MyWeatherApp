package Calander;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by akki on 24/4/15.
 */
public class DateView {

    public static String getDayName(int next) {
        int dayIndex = getCurrentDayIndex() - 1;
        dayIndex += next;
        dayIndex = dayIndex % 7;

        String[] days = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
        String day = days[dayIndex];
        return day;
    }

    /**
     *
     * @return  1,2,3,4,5,6 or 7
     */
    public static int getCurrentDayIndex() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        return dayIndex;
    }


    public static String getDateCurrentTimeZone(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd \n HH:mm:ss");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }
}






