package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class RateOfInterest extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private String particulars;

    private String scheduledIfAny;

    private Double sanctionPreCod;

    private Double sanctionPostCod;

    private Double presentRoi;

    private String freeText;

}
