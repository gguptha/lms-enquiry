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
public class SecurityCompliance extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private String collateralObjectType;

    private Integer quantity;

    private String applicability;

    private String collateralAgreementType; //(TypeofSecurity)

    private String timelines;

    private LocalDate dateOfCreation;

    private LocalDate validityDate;

    private Double value; //(ifany)

    private LocalDate securityPerfectionDate;

    private String remarks;

    //private LocalDate validityDate;

    private String actionPeriod;

    private String eventType;


    private String location;

    private String additionalText;

    private Integer realEstateLandArea;

    private String areaUnitOfMeasure;

    private Integer securityNoOfUnits;

    private Double securityFaceValueAmount;

    private Double holdingPercentage;
}
