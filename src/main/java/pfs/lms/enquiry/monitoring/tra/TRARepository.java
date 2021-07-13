package pfs.lms.enquiry.monitoring.tra;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.List;

public interface TRARepository extends JpaRepository<TrustRetentionAccount, String> {

    List<TrustRetentionAccount> findByLoanMonitor(LoanMonitor loanMonitor);

}
