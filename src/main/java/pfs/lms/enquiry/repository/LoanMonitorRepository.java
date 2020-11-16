package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.UUID;

public interface LoanMonitorRepository extends JpaRepository<LoanMonitor, UUID> {
}
