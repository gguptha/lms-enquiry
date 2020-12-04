package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;

import java.util.List;
import java.util.UUID;

public interface LIEReportAndFeeRepository extends JpaRepository<LIEReportAndFee, UUID> {

    List<LIEReportAndFee> findByLendersIndependentEngineer(LendersIndependentEngineer lendersIndependentEngineer);
}
