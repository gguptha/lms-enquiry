package pfs.lms.enquiry.appraisal.syndicateconsortium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pfs.lms.enquiry.appraisal.loanappraisalkyc.DefaultLoanAppraisalKYCProjection;
import pfs.lms.enquiry.appraisal.loanappraisalkyc.LoanAppraisalKYC;

import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultLoanAppraisalKYCProjection.class)
public interface BankRepository extends JpaRepository<LoanAppraisalKYC, UUID> {

}
