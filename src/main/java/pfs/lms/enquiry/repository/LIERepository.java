package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.List;
import java.util.UUID;

public interface LIERepository extends JpaRepository<LendersIndependentEngineer, String> {

    List<LendersIndependentEngineer> findByLoanMonitor(LoanMonitor loanMonitor);
}
