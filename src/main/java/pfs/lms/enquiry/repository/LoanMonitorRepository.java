package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.Optional;
import java.util.UUID;

public interface LoanMonitorRepository extends JpaRepository<LoanMonitor, UUID> {
    LoanMonitor findByLoanApplication(LoanApplication loanApplication);
}
