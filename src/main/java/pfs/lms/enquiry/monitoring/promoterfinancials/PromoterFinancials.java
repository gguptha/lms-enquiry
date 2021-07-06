package pfs.lms.enquiry.monitoring.promoterfinancials;

import lombok.*;
import pfs.lms.enquiry.domain.AbstractEntity;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PromoterFinancials extends AbstractEntity implements Cloneable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="borrowerFinancialsId")
    private BorrowerFinancials borrowerFinancialsId;//(Fkey)

    private Integer serialNumber;
    private Integer fiscalYear;
    private Double turnover;
    private Double pat;
    private Double netWorth;
    private LocalDate dateOfExternalRating;
    private LocalDate nextDueDateOfExternalRating;
    private String overAllRating;
    private String annualReturnFileReference;
    private String ratingFileReference;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
