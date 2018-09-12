package pfs.lms.enquiry.resource;

import lombok.Data;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;

@Data
public class LoanApplicationResource {

    private LoanApplication loanApplication;
    private Partner partner;
}
