package pfs.lms.enquiry.monitoring.tra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TRAStatementRepository extends JpaRepository<TrustRetentionAccountStatement, String> {

    List<TrustRetentionAccountStatement> findByTrustRetentionAccount(TrustRetentionAccount trustRetentionAccount);
}
