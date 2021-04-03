package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PromoterDetails extends AbstractEntity  implements Cloneable{

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private LocalDate dateOfChange;
    private Double groupExposure;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PromoterDetailsItem> promoterDetailsItemSet;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
