package in.theuniquemedia.brainbout.common;

import in.theuniquemedia.brainbout.common.util.CommonUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mahesh on 2/24/17.
 */
public class Test {
    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 01, 23, 0, 0, 0);
        System.out.println(CommonUtil.compareDates(calendar.getTime(), now));
        System.out.println(CommonUtil.getNoOfMinutesDiff(now, calendar.getTime()));
    }
}
