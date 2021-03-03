package pfs.lms.enquiry.monitoring.borrowerfinancials;

import lombok.*;
import pfs.lms.enquiry.domain.AbstractEntity;
import pfs.lms.enquiry.domain.LoanMonitor;

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
    private String annualReturnFileReference;
    private String ratingFileReference;
}