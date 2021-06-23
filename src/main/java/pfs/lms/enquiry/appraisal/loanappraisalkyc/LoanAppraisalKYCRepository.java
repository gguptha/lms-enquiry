package pfs.lms.enquiry.appraisal.loanappraisalkyc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultLoanAppraisalKYCProjection.class)
public interface LoanAppraisalKYCRepository extends JpaRepository<LoanAppraisalKYC, UUID> {

    List<LoanAppraisalKYC> findByLoanAppraisalId(UUID loanAppraisalId);

    List<LoanAppraisalKYC> findByLoanAppraisalIdOrderBySerialNumberDesc(UUID loanAppraisalId);

    List<LoanAppraisalKYC> findByLoanAppraisalLoanApplicationId(UUID loanApplicationId);
}
