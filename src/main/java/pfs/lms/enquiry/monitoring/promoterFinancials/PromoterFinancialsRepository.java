package pfs.lms.enquiry.monitoring.promoterFinancials;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.monitoring.promoterFinancials.PromoterFinancials;

import java.util.List;

public interface PromoterFinancialsRepository extends JpaRepository<PromoterFinancials, String> {

    List<PromoterFinancials> findByLoanMonitor(LoanMonitor loanMonitor);
}
