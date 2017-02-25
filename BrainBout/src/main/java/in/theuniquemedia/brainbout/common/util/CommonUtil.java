package in.theuniquemedia.brainbout.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mahesh on 2/24/17.
 */
public class CommonUtil {
    public static Integer compareDates(Date pDate1, Date pDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(sdf.format(pDate1));
            Date date2 = sdf.parse(sdf.format(pDate2));

            return date1.compareTo(date2);

        } catch (ParseException pe) {
            /*pe.printStackTrace();*/
            return null;
        }
    }

    public static Long getNoOfMinutesDiff(Date pDate1, Date pDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        try {
            Date date1 = sdf.parse(sdf.format(pDate1));
            Date date2 = sdf.parse(sdf.format(pDate2));
            long diff = date1.getTime() - date2.getTime();
            return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

        } catch (ParseException pe) {
            /*pe.printStackTrace();*/
            return null;
        }
    }

}

