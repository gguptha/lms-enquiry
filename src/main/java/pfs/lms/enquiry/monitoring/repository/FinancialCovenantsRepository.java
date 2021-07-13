package pfs.lms.enquiry.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.FinancialCovenants;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.List;

public interface FinancialCovenantsRepository extends JpaRepository<FinancialCovenants, String> {

    List<FinancialCovenants> findByLoanMonitor(LoanMonitor loanMonitor);
}
