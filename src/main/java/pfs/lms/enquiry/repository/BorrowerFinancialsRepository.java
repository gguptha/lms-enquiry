package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.BorrowerFinancials;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.SiteVisit;

import java.util.List;

public interface BorrowerFinancialsRepository extends JpaRepository<BorrowerFinancials, String> {

    List<BorrowerFinancials> findByLoanMonitor(LoanMonitor loanMonitor);
}
