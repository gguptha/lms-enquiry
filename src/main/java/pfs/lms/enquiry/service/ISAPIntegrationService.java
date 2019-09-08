package pfs.lms.enquiry.service;

import pfs.lms.enquiry.resource.SAPLoanApplicationDetailsResource;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;

public interface ISAPIntegrationService {

    String fetchCSRFToken();

    SAPLoanApplicationResource postLoanApplication(SAPLoanApplicationResource sapLoanApplicationResource);

    void getLoanApplication(String loanApplicationId);
}
