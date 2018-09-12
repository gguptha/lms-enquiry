package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanApplication extends AggregateRoot<LoanApplication>{

    private LocalDate loanEnquiryDate;

    private Integer loanEnquiryId;

    private Integer loanContractId;

    private UUID loanApplicant;

    /**
     * 001     Power
     * 002     Railways
     * 003     Urban Infra
     * 004     Roads
     * 005     Ports
     * 006     Oil & Gas
     * 007     Corporates
     * 008     Infrastructure
     * 009     Others
     */
    private Integer loanClass;

    /**
     * 01   Thermal - Coal
     * 02   Thermal - Ignite
     * 03   Thermal - Gas
     * 04   Hydro
     * 05   Renewable - Solar
     * 06   Renewable - Wind
     * 07   Renewable - Biomass
     * 08   Renewable - Small Hydro
     * 09   EPC Contractors
     * 10   Coal Mining
     * 11   Power Transmission
     * 12   Railway Siding
     * 13   Ports
     * 14   Corporate
     * 15   Renovation & Modernisation
     * 16   Others
     */
    private Integer projectType;

    /**
     * 01      Sole Lending
     * 02      Consor. Lending
     * 03      Lead FI
     * 04      Underwriting
     * 05      Lead FI & Synd.
     * 06      Syndication
     */
    private Integer financingType;

    /**
     * D - Debt
     * E - Equity
     */
    private String assistanceType;

    private Double projectCapacity;

    /**
     * KW-KiloWatt
     * MW-MegaWatt
     */
    private String projectCapacityUnit;


    @Size(max = 100)
    private String projectLocationState;

    @Size(max = 100)
    private String projectDistrict;

    private Integer tenorYear;

    private Integer tenorMonth;

    private Double projectCost;

    private Double projectDebtAmount;

    private Double equity;

    private String projectAmountCurrency = "INR";

    private Double expectedDebt;

    private Double pFSDebtAmount;

    private Double pFSSubDebtAmount;

    @Size(max = 100)
    private String loanPurpose;

    @Size(max = 100)
    private String leadFIName;

    private Double leanFILoanAmount;

    private Double expecttedInterestRate;

    private LocalDate scheduledCOD;

    @Size(max = 100)
    private String promoterName;

    private Double promoterNetWorthAmount;

    private Double promoterPATAmount;

    @Size(max = 100)
    private String promoterAreaofBusinessNature;

    private String rating;

    private String promoterKeyDirector;

    private String keyPromoter;


    /**
     * 01- Created
     * 02-Changed
     * 03-Submitted
     * 04-Approved
     * 05-Cancelled
     * 06-Rejected
     */
    private Integer technicalStatus;

    /**
     * 01-Enquiry Stage
     * 02-ICC ApprovalStage
     * 03-Apprisal Stage
     * 04-Board Approval Stage
     * 05-Loan Documenation Stage
     * 06-Loan Disbursement Stage
     * 07-Approved
     * 08-Rejected
     */
    private Integer functionalStatus;

    /**
     * 01-Approved
     * 02-Rejected
     */
    private Integer finalDecisionStatus;

    @Size(max = 100)
    private String rejectionReason;

    private LocalDate decisionDate;

    public LoanApplication(LocalDate loanEnquiryDate, Integer loanEnquiryId, Integer loanContractId, UUID loanApplicant, Integer loanClass, Integer projectType, Integer financingType, String assistanceType, Double projectCapacity, String projectCapacityUnit, @Size(max = 100) String projectLocationState, @Size(max = 100) String projectDistrict, Integer tenorYear, Integer tenorMonth, Double projectCost, Double projectDebtAmount, Double equity, String projectAmountCurrency, Double expectedDebt, Double pFSDebtAmount, Double pFSSubDebtAmount, @Size(max = 100) String loanPurpose, @Size(max = 100) String leadFIName, Double leanFILoanAmount, Double expecttedInterestRate, LocalDate scheduledCOD, @Size(max = 100) String promoterName, Double promoterNetWorthAmount, Double promoterPATAmount, @Size(max = 100) String promoterAreaofBusinessNature, String rating, String promoterKeyDirector, String keyPromoter,  Integer technicalStatus, Integer functionalStatus, Integer finalDecisionStatus, @Size(max = 100) String rejectionReason, LocalDate decisionDate) {
        this.loanEnquiryDate = loanEnquiryDate;
        this.loanEnquiryId = loanEnquiryId;
        this.loanContractId = loanContractId;
        this.loanApplicant = loanApplicant;
        this.loanClass = loanClass;
        this.projectType = projectType;
        this.financingType = financingType;
        this.assistanceType = assistanceType;
        this.projectCapacity = projectCapacity;
        this.projectCapacityUnit = projectCapacityUnit;
        this.projectLocationState = projectLocationState;
        this.projectDistrict = projectDistrict;
        this.tenorYear = tenorYear;
        this.tenorMonth = tenorMonth;
        this.projectCost = projectCost;
        this.projectDebtAmount = projectDebtAmount;
        this.equity = equity;
        this.projectAmountCurrency = projectAmountCurrency;
        this.expectedDebt = expectedDebt;
        this.pFSDebtAmount = pFSDebtAmount;
        this.pFSSubDebtAmount = pFSSubDebtAmount;
        this.loanPurpose = loanPurpose;
        this.leadFIName = leadFIName;
        this.leanFILoanAmount = leanFILoanAmount;
        this.expecttedInterestRate = expecttedInterestRate;
        this.scheduledCOD = scheduledCOD;
        this.promoterName = promoterName;
        this.promoterNetWorthAmount = promoterNetWorthAmount;
        this.promoterPATAmount = promoterPATAmount;
        this.promoterAreaofBusinessNature = promoterAreaofBusinessNature;
        this.rating = rating;
        this.promoterKeyDirector = promoterKeyDirector;
        this.keyPromoter = keyPromoter;
        this.technicalStatus = technicalStatus;
        this.functionalStatus = functionalStatus;
        this.finalDecisionStatus = finalDecisionStatus;
        this.rejectionReason = rejectionReason;
        this.decisionDate = decisionDate;


        registerEvent(LoanApplicationCreated.of(this));
    }

    public LoanApplication applicant(Partner partner){
        this.loanApplicant = partner.getId();
        return this;
    }

    public LoanApplication created(Partner partner){
        this.createdAt = LocalTime.now();
        this.createdOn = LocalDate.now();
        this.createdByUserName = partner.getUserName();
        return this;
    }

    public LoanApplication modified(Partner partner){
        this.changedAt = LocalTime.now();
        this.changedOn = LocalDate.now();
        this.changedByUserName = partner.getUserName();
        return this;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class LoanApplicationCreated {

        final LoanApplication loanApplication;
    }


}
