package pfs.lms.enquiry.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.repository.MailObjectRepository;
import pfs.lms.enquiry.mail.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * Created by sajeev on 16-Feb-19.
 */
@Component
public class EmailServiceImpl implements EmailService {
//
//    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    MailObjectRepository mailObjectRepository;

    public EmailServiceImpl() {
        emailSender = this.getJavaMailSender();

    }

    @Override
    public MailObject sendEmailMessage(MailObject mailObject) {

        //JavaMailSender emailSender = this.getJavaMailSender();

        try {
            SimpleMailMessage message = new SimpleMailMessage();



            message.setTo(mailObject.getToAddress());
            message.setSubject(mailObject.getSubject());
            message.setText(mailObject.getMailContent());

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mailObjectRepository.save(mailObject);

    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               SimpleMailMessage template,
                                               String ...templateArgs) {
        String text = String.format(template.getText(), templateArgs);
        sendSimpleMessage(to, subject, text);

    }



    @Override
    public void sendMessageWithAttachment(String to,
                                          String subject,
                                          String text,
                                          String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("pfsriskmodel@gmail.com");
        mailSender.setPassword("SapSap@123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
