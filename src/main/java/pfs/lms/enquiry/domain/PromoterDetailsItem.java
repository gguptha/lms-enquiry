package pfs.lms.enquiry.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PromoterDetailsItem extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PromoterDetails promoterDetails;

    private String shareHoldingCompany;
    private Double paidupCapitalEquitySanction;
    private Double paidupCapitalEquityCurrent;
    private Double equityLinkInstrumentSanction; // (CCD/ CCPS/ Unsecured loan) - Sanction
    private Double equityLinkInstrumentCurrent; // (CCD/ CCPS/ Unsecured loan) - Current
}
