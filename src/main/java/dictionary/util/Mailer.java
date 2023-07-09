package dictionary.util;

import dictionary.exceptionMessageStage.ExceptionMessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mailer.class);
    public static boolean send(String username) {

        if (username == null) {
            ExceptionMessageController.setText("This user is not exist");
            ExceptionMessageController.showStage();
            return false;
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String from = "dictionarynotice@gmail.com";
                    String to = "skodin36@gmail.com";

                    String host = "smtp.gmail.com";

                    String smtpPort = "465";

                    Properties properties = new Properties();

                    properties.put("mail.smtp.host", host);
                    properties.put("mail.smtp.port", smtpPort);
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, "nirsxlpowwcxtfdp");
                        }
                    });

//                    session.setDebug(LOGGER.isDebugEnabled());

                    try {
                        MimeMessage mimeMessage = new MimeMessage(session);
                        mimeMessage.setFrom(new InternetAddress(from));
                        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        mimeMessage.setSubject("Your password is: ");
                        mimeMessage.setText(username);
                        Transport.send(mimeMessage);
                    } catch (Exception e) {
//            System.err.println("Error");
                    }
                }
            }).start();
            return true;
        }

    }
}
