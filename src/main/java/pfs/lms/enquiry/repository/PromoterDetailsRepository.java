package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.PromoterDetails;

import java.util.List;

public interface PromoterDetailsRepository extends JpaRepository<PromoterDetails, String> {

    List<PromoterDetails> findByLoanMonitor(LoanMonitor loanMonitor);
}
