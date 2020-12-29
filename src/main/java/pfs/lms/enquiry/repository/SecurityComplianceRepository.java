package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.SecurityCompliance;

import java.util.List;

public interface SecurityComplianceRepository extends JpaRepository<SecurityCompliance, String> {

    List<SecurityCompliance> findByLoanMonitor(LoanMonitor loanMonitor);
}
