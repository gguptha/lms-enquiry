package pfs.lms.enquiry.service.workflow;

import pfs.lms.enquiry.resource.SAPLoanApplicationResource;

public interface ISAPLoanMonitorIntegrationService {

    String fetchCSRFToken();

    SAPLoanApplicationResource postLoanApplication(SAPLoanApplicationResource sapLoanApplicationResource);


    void getLoanApplication(String loanApplicationId);
}
