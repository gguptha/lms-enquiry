package pfs.lms.enquiry.domain;

import lombok.*;

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
public class PromoterDetailsItem extends AbstractEntity implements Cloneable {

    private Integer serialNumber;
    private String  shareHoldingCompany;
    private Double  paidupCapitalEquitySanction;
    private Double  paidupCapitalEquityCurrent;
    private Double  equityLinkInstrumentSanction; // (CCD/ CCPS/ Unsecured loan) - Sanction
    private Double  equityLinkInstrumentCurrent; // (CCD/ CCPS/ Unsecured loan) - Current

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
