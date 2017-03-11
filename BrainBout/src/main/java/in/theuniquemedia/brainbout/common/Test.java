package in.theuniquemedia.brainbout.common;

import in.theuniquemedia.brainbout.common.util.CommonUtil;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mahesh on 2/24/17.
 */
public class Test {
    public static void main(String[] args) {
        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
        shaPasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = shaPasswordEncoder.encodePassword("mahesh90", null);
        System.out.println(encodedPassword);
    }
}
