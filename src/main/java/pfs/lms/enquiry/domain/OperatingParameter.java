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
public class OperatingParameter extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private Integer serialNumber;
    private String month;
    private Integer year;
    private Double exportNetGeneration; //(Million Units)
    private Double plfCufActual; // (%age)
    private Double applicableTariff; // (Rs / Unit)
    private Double revenue; // (Rs in Lakhs)
    private LocalDate dateOfInvoice;
    private LocalDate dateOfPaymentReceipt;
    private Double carbonDioxideEmission; // (Tonnes)
    private Double waterSaved;
    private String remarks;
    private String designPlfCuf;
    private String actualYearlyAveragePlfCuf;
    private String documentType;
    private String documentTitle;
    private String fileReference;
}
