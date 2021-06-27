package pfs.lms.enquiry.appraisal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pfs.lms.enquiry.domain.LoanApplication;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultLoanAppraisalProjection.class)
public interface LoanAppraisalRepository extends JpaRepository<LoanAppraisal, UUID> {

    @RestResource(exported = false)
    Optional<LoanAppraisal> findByLoanApplication(LoanApplication loanApplication);
}
