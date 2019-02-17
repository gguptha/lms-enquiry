package pfs.lms.enquiry.mail.service;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface PasswordResetService {

    public String generatePassayPassword();
    public String sendMailWithNewPassword(String emailId, String fName, String lName);
    public String sendPasswordChangeNotificationMail(String emailId, String fName, String lName);

}
