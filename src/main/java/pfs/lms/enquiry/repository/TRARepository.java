package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.TrustRetentionAccount;

import java.util.List;

public interface TRARepository extends JpaRepository<TrustRetentionAccount, String> {

    List<TrustRetentionAccount> findByLoanMonitor(LoanMonitor loanMonitor);

}
