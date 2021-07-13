package pfs.lms.enquiry.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;
import pfs.lms.enquiry.monitoring.domain.RateOfInterest;

import java.util.List;

public interface RateOfInterestRepository extends JpaRepository<RateOfInterest, String> {

    List<RateOfInterest> findByLoanMonitor(LoanMonitor loanMonitor);
}
