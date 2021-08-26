package eapli.base.utils.emailsender;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private Properties prop = new Properties();
    final String username = "lapr4na2@gmail.com";
    final String password = "lapr42021";

    public EmailSender(){
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

    }

    public void sendEmail(Email email){

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email.to())
            );
            message.setSubject(email.subject());
            message.setText(email.body());

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }


    }


}
