package in.theuniquemedia.brainbout.common.service.impl;

import in.theuniquemedia.brainbout.common.service.IEmail;
import in.theuniquemedia.brainbout.common.vo.EmailVO;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by mahesh on 3/16/17.
 */
@Service
public class EmailUtil implements IEmail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    public boolean sendMail(final EmailVO emailVO){

        boolean status = true;
        try {

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {

                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                    message.setTo(emailVO.getReceiver());
                    message.setSubject(emailVO.getSubject());
                    message.setFrom(new InternetAddress(emailVO.getSender(), emailVO.getFromLabel()));
                    message.setReplyTo(emailVO.getSender());


                    message.setText(emailVO.getMessage(), true);
                }
            };

            JavaMailSenderImpl ms = (JavaMailSenderImpl) mailSender;
            ms.send(preparator);

        }catch (Exception ex) {
            ex.printStackTrace();
            status=false;
        }
        return status;
    }
}
