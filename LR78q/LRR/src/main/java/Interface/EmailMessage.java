package Interface;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailMessage {
    /**
     * Метод, що надсилає на пошту помилку та її назву
     * @param error назва помилки
     * @throws Exception для надсилання повідомлення
     */
    public void SendError (String error) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        properties.put("mail.smtp.host","smtp.ukr.net");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.Enable","true");
        properties.put("mail.smtp.user"," myemail@ukr.net ");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("myemail@ukr.net", "code");
            }
        };
        Session session = Session.getDefaultInstance(properties,auth);
        MimeMessage message = new MimeMessage(session);
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
        message.setFrom(new InternetAddress("myemail@ukr.net"));
        message.setReplyTo(InternetAddress.parse("myemail@ukr.net", false));
        message.setSubject("Під час виконання програми виникла критична помилка: ","UTF-8");
        message.setText(error,"UTF-8");
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("myemail@ukr.net"));
        Transport.send(message);
        System.out.print("\n Сталася критична помилка під час виконання програми, " +
                "деталі надіслано на електронну скриньку.");
    }
}