package pfs.lms.enquiry.mail.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.mail.config.MailConfig;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.repository.MailObjectRepository;
import pfs.lms.enquiry.mail.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by sajeev on 16-Feb-19.
 */
@Service
@Slf4j
 public class EmailServiceImpl implements EmailService {

    @Value( "${spring.mail.host}" )
    private String hostName;

    @Value( "${spring.mail.port}" )
    private String port;

    @Value( "${spring.mail.username}" )
    private String username;

    @Value( "${spring.mail.password}" )
    private String password;

    @Value( "${spring.mail.protocol}" )
    private String protocol;

    @Value( "${spring.mail.tls}" )
    private String tls;

    @Value( "${spring.mail.properties.mail.smtp.auth}" )
    private String auth;

    @Value( "${spring.mail.properties.mail.smtp.starttls.enable}" )
    private String starttlsEnable;

    @Value( "${spring.mail.properties.mail.smtp.ssl.trust}" )
    private String sslTrust;


//
//    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    MailObjectRepository mailObjectRepository;


    @Autowired
    MailConfig mailConfig;

    @Autowired
    private Environment env;

    public EmailServiceImpl() {

        //emailSender = this.getJavaMailSender();
    }

    @Override
    public MailObject sendEmailMessage(MailObject mailObject) {

        JavaMailSender emailSender = this.getJavaMailSender();


        try {
            SimpleMailMessage message = new SimpleMailMessage();


            message.setTo(mailObject.getToAddress());
            message.setSubject(mailObject.getSubject());
            message.setText(mailObject.getMailContent());
            message.setFrom(username); //"pfs_sapadmin@ptcfinancial.com");

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        mailObject.setDateTime(LocalDateTime.now());
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

        mailSender.setHost(hostName);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);
        props.put("mail.debug", "false");

        return mailSender;
    }
}
