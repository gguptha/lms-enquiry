package pfs.lms.enquiry.appraisal.loanpartner;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.UUID;

@Projection(name = "defaultLoanPartnerProjection", types = { LoanPartner.class })
public interface DefaultLoanPartnerProjection {

    UUID getId();

    Integer getSerialNumber();

    String getBusinessPartnerId();
    String getBusinessPartnerName();
    String getRoleType();

    LocalDate getStartDate();
}
