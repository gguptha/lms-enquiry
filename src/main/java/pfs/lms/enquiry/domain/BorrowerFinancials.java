package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BorrowerFinancials extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

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
