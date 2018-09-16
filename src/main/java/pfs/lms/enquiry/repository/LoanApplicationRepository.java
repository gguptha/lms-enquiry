package pfs.lms.enquiry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanApplication;

import java.util.UUID;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {
    Page<LoanApplication> findByCreatedByUserName(String username, Pageable pageable);
    Page<LoanApplication> findByCreatedByUserNameAndFunctionalStatus(String username, Integer status, Pageable pageable);
    Page<LoanApplication> findByLoanApplicant(UUID id, Pageable pageable);
    Page<LoanApplication> findByLoanApplicantAndFunctionalStatus(UUID id, Integer status, Pageable pageable);

}
