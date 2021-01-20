package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.SiteVisit;

import java.util.List;

public interface SiteVisitRepository extends JpaRepository<SiteVisit, String> {

    List<SiteVisit> findByLoanMonitor(LoanMonitor loanMonitor);
}
