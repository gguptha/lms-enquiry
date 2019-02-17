package pfs.lms.enquiry.mail.service;

import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface LoanNotificationService {

    public String sendSubmissionNotification(User user, LoanApplication loanApplication, Partner partner);
    public String sendApprovalNotification(User user, LoanApplication loanApplication, Partner partner);;
    public String sendRejectNotification(User user, LoanApplication loanApplication, Partner partner);
    public String sendCancelNotification(User user, LoanApplication loanApplication, Partner partner);
    public String sendUpdateNotification(User user, LoanApplication loanApplication, Partner partner);


}
