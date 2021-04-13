package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanContractExtension;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.Partner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoanMigrationResource {

    private LoanApplication loanApplication;
    private Partner partner;
    private LoanContractExtension loanContractExtension;
}
