package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class PromoterDetailItemResource {
    private UUID id;

    private Integer serialNumber;
    private String shareHoldingCompany;
    private Double paidupCapitalEquitySanction;
    private Double paidupCapitalEquityCurrent;
    private Double equityLinkInstrumentSanction;
    private Double equityLinkInstrumentCurrent;
}
