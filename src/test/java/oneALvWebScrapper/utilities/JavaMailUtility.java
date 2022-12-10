package oneALvWebScrapper.utilities;

import oneALvWebScrapper.models.MailCredentials;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JavaMailUtility {
    MailCredentials mailCredentials = new MailCredentials();

    public void sendMail(String subject, String textBody, String filePath) throws MessagingException {
        Properties properties = new Properties();

        System.out.println("Preparing to send email");

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myAccountEmail = mailCredentials.getEmail();
        String password = mailCredentials.getPassword();
        String recipient = mailCredentials.getRecipient();

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        if ("".equals(filePath)) {
            Message message = prepareMessageWithoutAttachment(session, myAccountEmail, recipient, subject, textBody);
            Transport.send(message);
        } else {
            Message message = prepareMessageWithAttachment(session, myAccountEmail, recipient, subject, textBody, filePath);
            Transport.send(message);
        }
        System.out.println("Message sent successfully!");
    }

    private Message prepareMessageWithoutAttachment(Session session, String myAccountEmail, String recipient, String emailSubject, String emailMessage) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(emailSubject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailMessage);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message prepareMessageWithAttachment(Session session, String myAccountEmail, String recipient, String emailSubject, String emailMessage, String filePath) {
        File emailAttachmentPath = new File(filePath);

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(emailSubject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            try {
                attachmentBodyPart.attachFile(emailAttachmentPath);
                messageBodyPart.setText(emailMessage);
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentBodyPart);
                message.setContent(multipart);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
