package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.*;
import pfs.lms.enquiry.domain.AggregateRoot;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PromoterDetailItem extends AggregateRoot<PromoterDetailItem> implements Cloneable    {

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
