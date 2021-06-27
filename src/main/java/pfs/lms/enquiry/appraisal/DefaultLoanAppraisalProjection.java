package pfs.lms.enquiry.appraisal;

import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "defaultLoanAppraisalProjection", types = { LoanAppraisal.class })
public interface DefaultLoanAppraisalProjection {

    UUID getId();

    String getProcessInstanceId();
    String getWorkFlowStatusDescription();

    Integer getWorkFlowStatusCode();
}
