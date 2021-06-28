package pfs.lms.enquiry.appraisal.syndicateconsortium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultSyndicateConsortiumProjection.class)
public interface SyndicateConsortiumRepository extends JpaRepository<SyndicateConsortium, UUID> {

    @RestResource(exported = false)
    List<SyndicateConsortium> findByLoanAppraisalId(UUID loanAppraisalId);

    List<SyndicateConsortium> findByLoanAppraisalLoanApplicationIdOrderBySerialNumberDesc(UUID loanApplicationId);
}
