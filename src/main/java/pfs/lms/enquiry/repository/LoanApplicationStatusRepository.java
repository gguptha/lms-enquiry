package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanApplicationStatus;

import java.util.UUID;

public interface LoanApplicationStatusRepository extends JpaRepository<LoanApplicationStatus, UUID> {
}
