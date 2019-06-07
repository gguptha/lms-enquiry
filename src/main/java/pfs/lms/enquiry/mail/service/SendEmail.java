package pfs.lms.enquiry.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.repository.MailObjectRepository;

import java.util.Properties;

/**
 * Created by sajeev on 06-Jun-19.
 */
@Component
public class SendEmail    {

//    MailObject mailObject;
//
//    public JavaMailSender emailSender;
//
//    @Autowired
//    MailObjectRepository mailObjectRepository;
//
//
//
//    public SendEmail(MailObject mailObject) {
//        this.mailObject = mailObject;
//        emailSender = this.getJavaMailSender();
//
//    }
//
//    @Override
//    public void run() {
//        try {
//
//            SimpleMailMessage message = new SimpleMailMessage();
//
//            message.setTo(mailObject.getToAddress());
//            message.setSubject(mailObject.getSubject());
//            message.setText(mailObject.getMailContent());
//
//            emailSender.send(message);
//        } catch (MailException exception) {
//            exception.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//          mailObjectRepository.save(mailObject);
//
//    }
//
//
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("pfsriskmodel@gmail.com");
//        mailSender.setPassword("SapSap@123");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
}
