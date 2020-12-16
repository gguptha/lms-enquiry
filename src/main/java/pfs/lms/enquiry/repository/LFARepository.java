package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersFinancialAdvisor;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.List;
import java.util.UUID;

public interface LFARepository extends JpaRepository<LendersFinancialAdvisor, UUID> {
    List<LendersFinancialAdvisor> findByLoanMonitor(LoanMonitor loanMonitor);
}
