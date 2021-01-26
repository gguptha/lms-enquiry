package pfs.lms.enquiry.domain;

import lombok.*;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PromoterFinancials extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="borrowerFinancialsId")
    private BorrowerFinancials borrowerFinancialsId;//(Fkey)

    private Integer fiscalYear;
    private Double turnover;
    private Double pat;
    private Double netWorth;
    private LocalDate dateOfExternalRating;
    private LocalDate nextDueDateOfExternalRating;

    private String overAllRating;
    private Blob documentContentAnnualReturn;
    private Blob documentContentRating;

}
