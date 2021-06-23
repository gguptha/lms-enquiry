package pfs.lms.enquiry.appraisal.loanappraisalkyc;

import org.springframework.data.rest.core.config.Projection;
import pfs.lms.enquiry.appraisal.DefaultLoanAppraisalProjection;

import java.time.LocalDate;
import java.util.UUID;

@Projection(name = "defaultLoanAppraisalKYCProjection", types = { LoanAppraisalKYC.class })
public interface DefaultLoanAppraisalKYCProjection {

    UUID getId();

    DefaultLoanAppraisalProjection getLoanAppraisal();

    Integer getSerialNumber();

    String getPartnerType();
    String getKycType();
    String getStatus();

    LocalDate getDateOfCompletion();

    String getRemarks();
    String getDocumentName();
    String getFileReference();

    LocalDate getUploadDate();
}
