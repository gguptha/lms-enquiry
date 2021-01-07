package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.PromoterDetailsItem;
import pfs.lms.enquiry.domain.PromoterFinancials;

import java.util.List;

public interface PromoterDetailsItemRepository extends JpaRepository<PromoterDetailsItem, String> {

    List<PromoterDetailsItem> findByLoanMonitor(LoanMonitor loanMonitor);
}
