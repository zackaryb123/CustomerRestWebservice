package com.restwebservice.cathibot.service;

import com.restwebservice.cathibot.model.Customer;
import com.restwebservice.cathibot.model.File;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {

    private JavaMailSender mailSender;

    public void movedFileMail(Customer customer, File file) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("CATHiBOT@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getCustomerEmail()));
        msg.setSubject("File "+file.getFileId()+": has been moved!");
        msg.setContent("CATHiBOT has moved file " + file.getFileId(), "text/html");
        msg.setSentDate(new Date());

//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("Tutorials point email", "text/html");

//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();

//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }
}
