package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.*;
import pfs.lms.enquiry.domain.AggregateRoot;
import pfs.lms.enquiry.domain.LoanMonitor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PromoterDetail extends AggregateRoot<PromoterDetail> implements Cloneable {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private LocalDate dateOfChange;
    private Double groupExposure;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PromoterDetailItem> promoterDetailItemSet;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
