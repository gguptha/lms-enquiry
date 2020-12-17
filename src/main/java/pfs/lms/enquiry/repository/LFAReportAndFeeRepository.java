package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LFAReportAndFee;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersFinancialAdvisor;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;

import java.util.List;
import java.util.UUID;

public interface LFAReportAndFeeRepository extends JpaRepository<LFAReportAndFee, String> {

    List<LFAReportAndFee> findByLendersFinancialAdvisor(LendersFinancialAdvisor lendersFinancialAdvisor);
}
