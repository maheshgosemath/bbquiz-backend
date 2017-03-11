package in.theuniquemedia.brainbout.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
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

    public static Date convertStringToDate(String utilDate, String format) {

        Date newDate = null;
        if (null != utilDate && null != format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                newDate = sdf.parse(utilDate);
            } catch (ParseException pe) {
                return null;
            }

        }
        return newDate;
    }

    public static String convertUtilDateToString(Date utilDate, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (utilDate == null) {
            return "";
        } else {
            return sdf.format(utilDate);
        }
    }

    public static String getRandomString() {
        return RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
    }

    public static String uploadFile(MultipartFile fileToBeUploaded, String dirPathPropKeyNm, String uniqueID) {
        File dir = null;
        File file = null;
        FileOutputStream fos = null;
        String filePath = "";
        try {
            if (fileToBeUploaded!=null) {
                String dirPath = dirPathPropKeyNm;
                dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                filePath = dirPath+uniqueID+fileToBeUploaded.getOriginalFilename();
                file = new File(filePath);
                fos = new FileOutputStream(file);
                fos.write(fileToBeUploaded.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

    public static String getUserName() {
        SecurityContext secCtx = SecurityContextHolder.getContext();
        Authentication auth = secCtx.getAuthentication();

        String userName = null;
        if (auth != null) {
            userName = auth.getName();
        }
        return userName;
    }
}

