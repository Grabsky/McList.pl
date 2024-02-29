package pl.xayanix.mclist.time;

import java.util.Calendar;

public class TimeUtil {

    public static long getEndOfDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        return calendar.getTimeInMillis();
    }

}
