package pfs.lms.enquiry.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;
import pfs.lms.enquiry.monitoring.domain.SiteVisit;

import java.util.List;

public interface SiteVisitRepository extends JpaRepository<SiteVisit, String> {

    List<SiteVisit> findByLoanMonitor(LoanMonitor loanMonitor);
}
