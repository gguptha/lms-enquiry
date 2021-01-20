package pfs.lms.enquiry.monitoring.lfa;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.lfa.LFAReportAndFee;
import pfs.lms.enquiry.monitoring.lfa.LendersFinancialAdvisor;

import java.util.List;

public interface LFAReportAndFeeRepository extends JpaRepository<LFAReportAndFee, String> {

    List<LFAReportAndFee> findByLendersFinancialAdvisor(LendersFinancialAdvisor lendersFinancialAdvisor);
}
