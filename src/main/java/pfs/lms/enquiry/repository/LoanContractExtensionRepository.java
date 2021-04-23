package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanContractExtension;

import java.util.UUID;

public interface LoanContractExtensionRepository extends JpaRepository<LoanContractExtension, UUID> {

    LoanContractExtension getLoanContractExtensionByLoanNumber(String loanNumber);

    LoanContractExtension getLoanContractExtensionByLoanApplication(LoanApplication loanApplication);
}
