package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.LoanContractExtension;
import pfs.lms.enquiry.resource.LoanContractExtensionResource;

public interface ILoanContractExtensionService {

    LoanContractExtension save(LoanContractExtensionResource resource, String username);
    LoanContractExtension update(LoanContractExtensionResource resource, String username);

}
