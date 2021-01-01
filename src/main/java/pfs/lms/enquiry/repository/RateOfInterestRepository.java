package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.RateOfInterest;
import pfs.lms.enquiry.domain.SiteVisit;

import java.util.List;

public interface RateOfInterestRepository extends JpaRepository<RateOfInterest, String> {

    List<RateOfInterest> findByLoanMonitor(LoanMonitor loanMonitor);
}
