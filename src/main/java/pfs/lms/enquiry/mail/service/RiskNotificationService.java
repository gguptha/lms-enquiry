package pfs.lms.enquiry.mail.service;

import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface RiskNotificationService {

    public String sendOfficerAssignmentNotification(User user, LoanApplication loanApplication , String assignmentType);
    public String sendRiskOfficerAssignmentNotification(User user, LoanApplication loanApplication );;



}
