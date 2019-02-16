package pfs.lms.enquiry.mail.service;

import org.springframework.mail.SimpleMailMessage;
import pfs.lms.enquiry.mail.domain.MailObject;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface EmailService {


    public MailObject sendEmailMessage(MailObject mailObject);

    void sendSimpleMessage(String to,
                           String subject,
                           String text);

        void sendSimpleMessageUsingTemplate(String to,
                                            String subject,
                                            SimpleMailMessage template,
                                            String... templateArgs);

        void sendMessageWithAttachment(String to,
                                       String subject,
                                       String text,
                                       String pathToAttachment);

    }
