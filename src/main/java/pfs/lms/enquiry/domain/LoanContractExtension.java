package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LoanContractExtension extends AggregateRoot<LoanContractExtension> {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private LoanApplication loanApplication;

    private String boardMeetingNumber;

    private LocalDate boardApprovalDate;

    private String loanNumber;

    private LocalDate sanctionLetterDate;

    private LocalDate loanDocumentationDate;

    private LocalDate firstDisbursementDate;

    private Double sanctionAmount;

    private String disbursementStatus;

    private LocalDate scheduledCOD;

}
