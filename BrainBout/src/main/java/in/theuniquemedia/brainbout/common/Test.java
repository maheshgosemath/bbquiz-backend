package in.theuniquemedia.brainbout.common;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Created by mahesh on 2/24/17.
 */
public class Test {
    public static void main(String[] args) {
        /*ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
        shaPasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = shaPasswordEncoder.encodePassword("mahesh90", null);
        System.out.println(encodedPassword);*/

        for(int i=4;i<=153;i++) {
            for(int j=1;j<=4;j++) {
                char isCorrect = 'N';
                if(j == 2) {
                    isCorrect = 'Y';
                }
                String s = "INSERT INTO `brainbout`.`quiz_options` (`QUIZ_SEQ`, `OPTION_TITLE`, `IS_CORRECT`, `STATUS`) " +
                        "VALUES ("+ i +", 'Option "+ j +"', '" + String.valueOf(isCorrect) + "', 'A');\n";
                System.out.println(s);
            }
        }

    }
}