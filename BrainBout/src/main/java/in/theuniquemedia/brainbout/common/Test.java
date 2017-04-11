package in.theuniquemedia.brainbout.common;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mahesh on 2/24/17.
 */
public class Test {
    public static void main(String[] args) {
        /*ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
        shaPasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = shaPasswordEncoder.encodePassword("mahesh90", null);
        System.out.println(encodedPassword);*/

        final String username = "info@brainbout.in";
        final String password = "ykd0wv&!I$Ot";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sdst-ngmy.accessdomain.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.false", password);
        props.put("mail.smtps.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.transport.protocol", true);

        Session session = Session.getInstance(props);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@brainbout.in"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("maheshgosemath@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport transport = session.getTransport ("smtp");
            transport.connect("sdst-ngmy.accessdomain.com", "info@brainbout.in",password);
            transport.sendMessage(message, message.getAllRecipients ());
            transport.close ();

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}