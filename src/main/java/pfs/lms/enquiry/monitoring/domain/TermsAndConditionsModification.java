package pfs.lms.enquiry.monitoring.domain;

import lombok.*;
import pfs.lms.enquiry.domain.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TermsAndConditionsModification extends AbstractEntity implements Cloneable{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private Integer serialNumber;
    private String documentType;
    private String documentTitle;
    private String communication;
    private LocalDate borrowerRequestLetterDate;
    private LocalDate dateofIssueofAmendedSanctionLetter;
    private String remarks;
    private String fileReference;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
