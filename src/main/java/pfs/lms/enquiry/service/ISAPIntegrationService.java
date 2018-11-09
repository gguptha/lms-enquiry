package pfs.lms.enquiry.service;

import pfs.lms.enquiry.resource.SAPLoanApplicationResource;

public interface ISAPIntegrationService {

    String fetchCSRFToken();
    void postLoanApplication(SAPLoanApplicationResource sapLoanApplicationResource);
}
