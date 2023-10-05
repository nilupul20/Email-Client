import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail implements Serializable {
    private static final SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
    private transient String username = "sandarusiru@gmail.com";
    private transient String password = "aciacbjwjyjzlksz";
    private String recipient;
    private String subject;
    private String content;
    private String date = sdformat.format(new Date());

    public Mail(String recipient, String subject, String content){
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
    public void sendMail(){
        Properties prop = new Properties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient( Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email sent successfully.");

            // serialize the mail
            MailStore mailStore = MailStore.getInstance();
            mailStore.serializeMail(this);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String toString(){
        return "Recipient : " + recipient + "\nSubject : " + subject + "\n" + content + "\n";
    }

    public String getDate() {
        return date;
    }
}
