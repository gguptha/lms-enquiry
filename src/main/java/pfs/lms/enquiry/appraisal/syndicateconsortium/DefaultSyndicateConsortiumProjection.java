package pfs.lms.enquiry.appraisal.syndicateconsortium;

import org.springframework.data.rest.core.config.Projection;
import pfs.lms.enquiry.appraisal.DefaultLoanAppraisalProjection;

import java.util.UUID;

@Projection(name = "defaultSyndicateConsortiumProjection", types = { SyndicateConsortium.class })
public interface DefaultSyndicateConsortiumProjection {

    UUID getId();

    DefaultLoanAppraisalProjection getLoanAppraisal();

    Integer getSerialNumber();
    Double getSanctionedAmount();
    String getCurrency();
    boolean getLead();
    String getApprovalStatus();
    String getDocumentStage();
    Double getDisbursedAmount();
    String getDisbursementStatus();
}
