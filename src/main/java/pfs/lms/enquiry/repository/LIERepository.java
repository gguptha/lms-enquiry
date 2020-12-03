package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;

import java.util.List;
import java.util.UUID;

public interface LIERepository extends JpaRepository<LendersIndependentEngineer, UUID> {

    List<LendersIndependentEngineer> findByLoanMonitor(UUID loanMonitorId);
}
