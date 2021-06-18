package pfs.lms.enquiry.appraisal.loanpartner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultLoanPartnerProjection.class)
public interface LoanPartnerRepository extends JpaRepository<LoanPartner, UUID> {

    List<LoanPartner> findByLoanApplicationIdOrderBySerialNumberDesc(UUID loanApplicationId);
}
