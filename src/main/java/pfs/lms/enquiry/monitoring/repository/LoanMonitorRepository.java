package pfs.lms.enquiry.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.UUID;

public interface LoanMonitorRepository extends JpaRepository<LoanMonitor, UUID> {
    LoanMonitor findByLoanApplication(LoanApplication loanApplication);
}
