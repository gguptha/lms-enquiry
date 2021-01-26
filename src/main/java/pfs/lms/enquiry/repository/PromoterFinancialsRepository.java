package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.PromoterFinancials;

import java.util.List;

public interface PromoterFinancialsRepository extends JpaRepository<PromoterFinancials, String> {

    List<PromoterFinancials> findByLoanMonitor(LoanMonitor loanMonitor);
}
