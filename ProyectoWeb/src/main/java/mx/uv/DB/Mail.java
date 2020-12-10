package mx.uv.DB;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
    
    public static void enviarEmail(String usuario, String destinatario) {
        String remitente = "marabunta.gimnasio@gmail.com";
        String contraseñaRemitente = "sw-proyecto";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", remitente);
        properties.put("mail.password", contraseñaRemitente);

        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(remitente, "Gimnasio Marabunta"));

            InternetAddress[] internetAddresses = {new InternetAddress(destinatario)};
            mimeMessage.setRecipients(Message.RecipientType.TO,internetAddresses);
            mimeMessage.setSubject("Su nueva cuenta en el Gimnasio Marabunta");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            String cuerpo;
            cuerpo = "Usted acaba de crear su cuenta en el Gimnasio Marabunta!\n\tBienvenido!\n\nNombre de usuario: " + usuario;
            mimeBodyPart.setText(cuerpo);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            mimeMessage.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(remitente, contraseñaRemitente);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }catch (MessagingException ex) {
            System.out.println(ex.toString());
        }catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        }
    }
}
