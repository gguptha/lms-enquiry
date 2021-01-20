package pfs.lms.enquiry.monitoring.lie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LIEReportAndFeeRepository extends JpaRepository<LIEReportAndFee, String> {

    List<LIEReportAndFee> findByLendersIndependentEngineer(LendersIndependentEngineer lendersIndependentEngineer);
}
