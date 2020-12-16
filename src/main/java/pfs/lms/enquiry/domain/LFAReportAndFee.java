package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LFAReportAndFee extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LendersFinancialAdvisor lendersFinancialAdvisor;

    private String reportType;

    private LocalDate quarterOrYear;

    private LocalDate dateOfReceipt;

    private LocalDate invoiceDate;

    private String invoiceNo;

    private Double feeAmount;

    private String statusOfFeeReceipt;

    private String statusOfFeePaid;

    private String documentType;

    private String documentTitle;

    private Blob documentContent;

    private LocalDate nextReportDate;
}
