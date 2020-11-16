package pfs.lms.enquiry.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LIEReportAndFee extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LendersIndependentEngineer lendersIndependentEngineer;

    private String reportType;

    private LocalDate dateOfReceipt;

    private LocalDate invoiceDate;

    private String invoiceNo;

    private Double feeAmount;

    private String statusOfFeeReceipt;

    private String statusOfFeePaid;

    private String documentTitle;

    private Blob documentContent;

    private LocalDate nextReportDate;
}
