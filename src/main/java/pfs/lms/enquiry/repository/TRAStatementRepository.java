package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.TrustRetentionAccount;
import pfs.lms.enquiry.domain.TrustRetentionAccountStatement;

import java.util.List;

public interface TRAStatementRepository extends JpaRepository<TrustRetentionAccountStatement, String> {

    List<TrustRetentionAccountStatement> findByTrustRetentionAccount(TrustRetentionAccount trustRetentionAccount);
}
