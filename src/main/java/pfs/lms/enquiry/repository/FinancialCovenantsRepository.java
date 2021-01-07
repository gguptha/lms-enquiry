package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.FinancialCovenants;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.List;

public interface FinancialCovenantsRepository extends JpaRepository<FinancialCovenants, String> {

    List<FinancialCovenants> findByLoanMonitor(LoanMonitor loanMonitor);
}
