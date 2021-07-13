package pfs.lms.enquiry.monitoring.lfa;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.List;

public interface LFARepository extends JpaRepository<LendersFinancialAdvisor, String> {
    List<LendersFinancialAdvisor> findByLoanMonitor(LoanMonitor loanMonitor);
}
