package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.resource.LoanApplicationResource;

public interface ILoanApplicationService {

    LoanApplication save(LoanApplicationResource resource, String username);


    LoanApplication migrate(LoanApplicationResource resource, String username);
    LoanApplication migrateUpdate(LoanApplication loanApplication, Partner partner, String username);

}
