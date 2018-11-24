package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class LoanApplication extends AggregateRoot<LoanApplication> {

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private EnquiryNo enquiryNo;

    private LocalDate loanEnquiryDate;

    private Integer loanEnquiryId;

    private String loanContractId;

    @Type(type = "uuid-char")
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
    private String loanClass;

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
    private String projectType;

    /**
     * 01      Sole Lending
     * 02      Consor. Lending
     * 03      Lead FI
     * 04      Underwriting
     * 05      Lead FI & Synd.
     * 06      Syndication
     */
    private String financingType;

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

    private Double expectedSubDebt;

    private Double pfsDebtAmount;

    private Double pfsSubDebtAmount;

    @Size(max = 100)
    private String loanPurpose;

    @Size(max = 100)
    private String leadFIName;

    private Double leadFILoanAmount;

    private Double expectedInterestRate;

    private LocalDate scheduledCOD;

    @Size(max = 100)
    private String promoterName;

    private Double promoterNetWorthAmount;

    private Double promoterPATAmount;

    @Size(max = 100)
    private String promoterAreaOfBusinessNature;

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

    private LocalDateTime rejectionDate;

    private LocalDate decisionDate;

    private String userBPNumber;

    private String groupCompany;

    private String productCode;

    private String busPartnerNumber;

    @JsonCreator
    public LoanApplication(LocalDate loanEnquiryDate, Integer loanEnquiryId, String loanContractId, UUID loanApplicant, String loanClass, String projectType, String financingType, String assistanceType, Double projectCapacity, String projectCapacityUnit, @Size(max = 100) String projectLocationState, @Size(max = 100) String projectDistrict, Integer tenorYear, Integer tenorMonth, Double projectCost, Double projectDebtAmount, Double equity, String projectAmountCurrency, Double expectedSubDebt, Double pfsDebtAmount, Double pfsSubDebtAmount, @Size(max = 100) String loanPurpose, @Size(max = 100) String leadFIName, Double leadFILoanAmount, Double expectedInterestRate, LocalDate scheduledCOD, @Size(max = 100) String promoterName, Double promoterNetWorthAmount, Double promoterPATAmount, @Size(max = 100) String promoterAreaOfBusinessNature, String rating, String promoterKeyDirector, String keyPromoter, Integer technicalStatus, Integer finalDecisionStatus, @Size(max = 100) String rejectionReason, LocalDate decisionDate, String userBPNumber, String groupCompany, String productCode, String busPartnerNumber) {

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
        this.expectedSubDebt = expectedSubDebt;
        this.pfsDebtAmount = pfsDebtAmount;
        this.pfsSubDebtAmount = pfsSubDebtAmount;
        this.loanPurpose = loanPurpose;
        this.leadFIName = leadFIName;
        this.leadFILoanAmount = leadFILoanAmount;
        this.expectedInterestRate = expectedInterestRate;
        this.scheduledCOD = scheduledCOD;
        this.promoterName = promoterName;
        this.promoterNetWorthAmount = promoterNetWorthAmount;
        this.promoterPATAmount = promoterPATAmount;
        this.promoterAreaOfBusinessNature = promoterAreaOfBusinessNature;
        this.rating = rating;
        this.promoterKeyDirector = promoterKeyDirector;
        this.keyPromoter = keyPromoter;
        this.technicalStatus = technicalStatus;
        this.functionalStatus = 01;
        this.finalDecisionStatus = finalDecisionStatus;
        this.rejectionReason = rejectionReason;
        this.decisionDate = decisionDate;
        this.userBPNumber = userBPNumber;
        this.groupCompany = groupCompany;
        this.productCode = productCode;
        this.busPartnerNumber = busPartnerNumber;
        this.enquiryNo = new EnquiryNo();
        registerEvent(LoanApplicationCreated.of(this));
    }

    public LoanApplication applicant(Partner partner) {
        this.loanApplicant = partner.getId();
        return this;
    }

    public LoanApplication created(Partner partner) {
        this.createdAt = LocalTime.now();
        this.createdOn = LocalDate.now();
        this.createdByUserName = partner.getUserName();
        return this;
    }

    public LoanApplication modified(Partner partner) {
        this.changedAt = LocalTime.now();
        this.changedOn = LocalDate.now();
        this.changedByUserName = partner.getUserName();
        return this;
    }

    public LoanApplication reject(String reason,Partner modified){
        this.functionalStatus = 8;
        this.finalDecisionStatus = 2;
        this.rejectionReason = reason;
        this.rejectionDate = LocalDateTime.now();
        registerEvent(LoanApplicationRejected.of(this));
        return this;
    }

    public LoanApplication responseFromSAP(SAPLoanApplicationResource sapLoanApplicationResource){
        this.functionalStatus = 2;
        this.loanContractId = sapLoanApplicationResource.getSapLoanApplicationDetailsResource().getLoanContract();
        this.busPartnerNumber = sapLoanApplicationResource.getSapLoanApplicationDetailsResource().getBusPartnerNumber();
        return this;
    }

    public EnquiryNo getEnquiryNo() {
        return this.enquiryNo;
    }

    public LocalDate getLoanEnquiryDate() {
        return this.loanEnquiryDate;
    }

    public Integer getLoanEnquiryId() {
        return this.loanEnquiryId;
    }

    public String getLoanContractId() {
        return this.loanContractId;
    }

    public UUID getLoanApplicant() {
        return this.loanApplicant;
    }

    public String getLoanClass() {
        return this.loanClass;
    }

    public String getProjectType() {
        return this.projectType;
    }

    public String getFinancingType() {
        return this.financingType;
    }

    public String getAssistanceType() {
        return this.assistanceType;
    }

    public Double getProjectCapacity() {
        return this.projectCapacity;
    }

    public String getProjectCapacityUnit() {
        return this.projectCapacityUnit;
    }

    public @Size(max = 100) String getProjectLocationState() {
        return this.projectLocationState;
    }

    public @Size(max = 100) String getProjectDistrict() {
        return this.projectDistrict;
    }

    public Integer getTenorYear() {
        return this.tenorYear;
    }

    public Integer getTenorMonth() {
        return this.tenorMonth;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public Double getProjectDebtAmount() {
        return this.projectDebtAmount;
    }

    public Double getEquity() {
        return this.equity;
    }

    public String getProjectAmountCurrency() {
        return this.projectAmountCurrency;
    }

    public Double getExpectedSubDebt() {
        return this.expectedSubDebt;
    }

    public Double getPfsDebtAmount() {
        return this.pfsDebtAmount;
    }

    public Double getPfsSubDebtAmount() {
        return this.pfsSubDebtAmount;
    }

    public @Size(max = 100) String getLoanPurpose() {
        return this.loanPurpose;
    }

    public @Size(max = 100) String getLeadFIName() {
        return this.leadFIName;
    }

    public Double getLeadFILoanAmount() {
        return this.leadFILoanAmount;
    }

    public Double getExpectedInterestRate() {
        return this.expectedInterestRate;
    }

    public LocalDate getScheduledCOD() {
        return this.scheduledCOD;
    }

    public @Size(max = 100) String getPromoterName() {
        return this.promoterName;
    }

    public Double getPromoterNetWorthAmount() {
        return this.promoterNetWorthAmount;
    }

    public Double getPromoterPATAmount() {
        return this.promoterPATAmount;
    }

    public @Size(max = 100) String getPromoterAreaOfBusinessNature() {
        return this.promoterAreaOfBusinessNature;
    }

    public String getRating() {
        return this.rating;
    }

    public String getPromoterKeyDirector() {
        return this.promoterKeyDirector;
    }

    public String getKeyPromoter() {
        return this.keyPromoter;
    }

    public Integer getTechnicalStatus() {
        return this.technicalStatus;
    }

    public Integer getFunctionalStatus() {
        return this.functionalStatus;
    }

    public Integer getFinalDecisionStatus() {
        return this.finalDecisionStatus;
    }

    public LocalDateTime getRejectionDate() {
        return rejectionDate;
    }

    public String getUserBPNumber() {
        return userBPNumber;
    }

    public String getGroupCompany() {
        return this.groupCompany;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public @Size(max = 100) String getRejectionReason() {
        return this.rejectionReason;
    }

    public LocalDate getDecisionDate() {
        return this.decisionDate;
    }


    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class LoanApplicationApproved {

        final LoanApplication loanApplication;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class LoanApplicationCreated {

        final LoanApplication loanApplication;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class LoanApplicationRejected {

        final LoanApplication loanApplication;
    }
}
