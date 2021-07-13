package pfs.lms.enquiry.monitoring.borrowerfinancials;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.List;

public interface BorrowerFinancialsRepository extends JpaRepository<BorrowerFinancials, String> {

    List<BorrowerFinancials> findByLoanMonitor(LoanMonitor loanMonitor);
}
