package pfs.lms.enquiry.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.service.EmailService;
import pfs.lms.enquiry.mail.service.LoanNotificationService;

/**
 * Created by sajeev on 17-Feb-19.
 */
@Component
public class LoanNotificationServiceImpl implements LoanNotificationService {

    @Autowired
    EmailService emailService;

    @Override
    public String sendSubmissionNotification(User user, LoanApplication loanApplication, Partner partner) {

        String line1 = "Dear" + " " + user.getFirstName() + " " + user.getLastName() + System.lineSeparator();
        String line2 = "    Your loan application with the following details is submitted for further processing by PTC Financial Services." + System.lineSeparator();
        String line3 = "    Loan Application Id  : " +loanApplication.getEnquiryNo() + System.lineSeparator() ;
        String line4 = "    Project Name         : " +loanApplication.getProjectName() + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Loan Enquiry Portal");
        mailObject.setAppObjectId(loanApplication.getEnquiryNo().toString());
        mailObject.setToAddress(user.getEmail());
        mailObject.setSubject("Your loan application for the project " +loanApplication.getProjectName() + " is submitted to PTC Financial Services");
        mailObject.setMailContent(content);

        mailObject = emailService.sendEmailMessage(mailObject);
        return mailObject.getId().toString();
    }

    @Override
    public String sendApprovalNotification(User user, LoanApplication loanApplication, Partner partner) {

        String line1 = "Dear" + " " + user.getFirstName() + " " + user.getLastName() + System.lineSeparator();
        String line2 = "    Your loan application with the following details is taken up for processing by PTC Financial Services." + System.lineSeparator();
        String line3 = "    Loan Number  : " +loanApplication.getLoanContractId() + System.lineSeparator() ;
        String line4 = "    Project Name : " +loanApplication.getProjectName() + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Loan Enquiry Portal");
        mailObject.setAppObjectId(loanApplication.getEnquiryNo().toString());
        mailObject.setToAddress(partner.getEmail());
        mailObject.setSubject("Your loan application for the project " +loanApplication.getProjectName() + " is being processed by PTC Financial Services");
        mailObject.setMailContent(content);

        mailObject = emailService.sendEmailMessage(mailObject);
        return mailObject.getId().toString();
    }

    @Override
    public String sendRejectNotification(User user, LoanApplication loanApplication, Partner partner) {

        String line1 = "Dear" + " " + user.getFirstName() + " " + user.getLastName() + System.lineSeparator();
        String line2 = "    Your loan application with the following details is rejected by PTC Financial Services." + System.lineSeparator();
        String line3 = "    Loan Application Id  : " +loanApplication.getEnquiryNo() + System.lineSeparator() ;
        String line4 = "    Project Name         : " +loanApplication.getProjectName() + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Loan Enquiry Portal");
        mailObject.setAppObjectId(loanApplication.getId().toString());
        mailObject.setToAddress(user.getEmail());
        mailObject.setSubject("Your loan application for the project " +loanApplication.getProjectName() + " is rejected to PTC Financial Services");
        mailObject.setMailContent(content);

        mailObject = emailService.sendEmailMessage(mailObject);
        return mailObject.getId().toString();


    }

    @Override
    public String sendCancelNotification(User user, LoanApplication loanApplication, Partner partner) {

        String line1 = "Dear" + " " + user.getFirstName() + " " + user.getLastName() + System.lineSeparator();
        String line2 = "    Your loan application with the following details is cancelled" + System.lineSeparator();
        String line3 = "    Loan Application Id  : " +loanApplication.getEnquiryNo() + System.lineSeparator() ;
        String line4 = "    Project Name         : " +loanApplication.getProjectName() + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Loan Enquiry Portal");
        mailObject.setAppObjectId(loanApplication.getId().toString());
        mailObject.setToAddress(user.getEmail());
        mailObject.setSubject("Your loan application for the project " +loanApplication.getProjectName() + " is cancelled");
        mailObject.setMailContent(content);

        mailObject = emailService.sendEmailMessage(mailObject);
        return mailObject.getId().toString();
    }

    @Override
    public String sendUpdateNotification(User user, LoanApplication loanApplication, Partner partner) {

        String line1 = "Dear" + " " + user.getFirstName() + " " + user.getLastName() + System.lineSeparator();
        String line2 = "    Your loan application with the following details is updated" + System.lineSeparator();
        String line3 = "    Loan Application Id  : " +loanApplication.getEnquiryNo() + System.lineSeparator() ;
        String line4 = "    Project Name         : " +loanApplication.getProjectName() + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Loan Enquiry Portal");
        mailObject.setAppObjectId(loanApplication.getId().toString());
        mailObject.setToAddress(user.getEmail());
        mailObject.setSubject("Your loan application for the project " +loanApplication.getProjectName() + " is updated");
        mailObject.setMailContent(content);

        mailObject = emailService.sendEmailMessage(mailObject);
        return mailObject.getId().toString();

    }
}
