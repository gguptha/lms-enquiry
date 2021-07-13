package pfs.lms.enquiry.monitoring.domain;

import lombok.*;
import pfs.lms.enquiry.domain.AbstractEntity;

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
public class RateOfInterest extends AbstractEntity implements Cloneable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private Integer serialNumber;

    private String particulars;

    private String scheduledIfAny;

    private Double sanctionPreCod;

    private Double sanctionPostCod;

    private Double presentRoi;

    private String freeText;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

}
