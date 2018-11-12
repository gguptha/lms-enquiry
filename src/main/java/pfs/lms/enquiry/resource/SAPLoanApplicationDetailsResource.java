package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Created by gguptha on 09/11/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SAPLoanApplicationDetailsResource {

    @JsonProperty (value = "LoanContract")
    private String loanContract;

    @JsonProperty(value = "LoanApplicationId")
    private String loanApplicationId;

    @JsonProperty(value = "BusPartnerNumber")
    private String busPartnerNumber;

    @JsonProperty(value = "PartnerCategory")
    private String partnerCategory;

    @JsonProperty(value = "PartnerType")
    private String partnerType;

    @JsonProperty(value = "PartnerGroup")
    private String partnerGroup;

    @JsonProperty(value = "PartnerExternalNumber")
    private String partnerExternalNumber;

    @JsonProperty(value = "PartnerRole")
    private String partnerRole;

    @JsonProperty(value = "Name1")
    private String name1;

    @JsonProperty(value = "Name2")
    private String name2;

    @JsonProperty(value = "Firstname")
    private String firstname;

    @JsonProperty(value = "Lastname")
    private String lastname;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "City")
    private String city;

    @JsonProperty(value = "District")
    private String district;

    @JsonProperty(value = "Regiogroup")
    private String regiogroup;

    @JsonProperty(value = "PostalCode")
    private String postalCode;

    @JsonProperty(value = "HouseNo")
    private String houseNo;

    @JsonProperty(value = "Street")
    private String street;

    @JsonProperty(value = "Country")
    private String country;

    @JsonProperty(value = "ContactPerName")
    private String contactPerName;


    @JsonProperty(value = "PanNumber")
    private String panNumber;

    @JsonProperty(value = "ApplicationDate")
    private LocalDate applicationDate;

    @JsonProperty(value = "LoanClass")
    private String loanClass;

    @JsonProperty(value = "FinancingType")
    private String financingType;

    @JsonProperty(value = "DebtEquityIndicator")
    private String debtEquityIndicator;

    @JsonProperty(value = "ProjectCapaacity")
    private Double projectCapaacity;

    @JsonProperty(value = "ProjectCapacityUnit")
    private String projectCapacityUnit;

    @JsonProperty(value = "ProjectState")
    private String projectState;

    @JsonProperty(value = "ProjectDistrict")
    private String projectDistrict;

    @JsonProperty(value = "TenorYear")
    private Integer tenorYear;

    @JsonProperty(value = "TenorMonth")
    private Integer tenorMonth;


    @JsonProperty(value = "ProjectCostInCrores")
    private Double projectCostInCrores;


    @JsonProperty(value = "DebtAmountInCrores")
    private Double debtAmountInCrores;

    @JsonProperty(value = "EquityAmountInCrores")
    private Double equityAmountInCrores;

    @JsonProperty(value = "Currency")
    private String currency;

    @JsonProperty(value = "ApplicationCapitalInCrores")
    private Double applicationCapitalInCrores;

    @JsonProperty(value = "LoanPurpose")
    private String loanPurpose;

    @JsonProperty(value = "ScheduledCommDate")
    private LocalDate scheduledCommDate;

    @JsonProperty(value = "GroupCompanyName")
    private String groupCompanyName;

    @JsonProperty(value = "PromoterName")
    private String promoterName;

    @JsonProperty(value = "PromoterNetWorthInCrores")
    private  Double promoterNetWorthInCrores;

    @JsonProperty(value = "PromoterPATInCrores")
    private Double promoterPATInCrores;

    @JsonProperty(value = "PromoterAreaOfBusiness")
    private String promoterAreaOfBusiness;

    @JsonProperty(value = "PromoterRating")
    private String promoterRating;

    @JsonProperty(value = "PromoterKeyDirector")
    private String promoterKeyDirector;

    @JsonProperty(value = "LoanStatus")
    private String loanStatus;

    @JsonProperty(value = "ProjectName")
    private String projectName;

    public SAPLoanApplicationDetailsResource() {

        this.loanContract = "";
        this.loanApplicationId = "";
        this.busPartnerNumber = "";
        this.partnerCategory = "";
        this.partnerType = "";
        this.partnerGroup = "";
        this.partnerExternalNumber = "";
        this.partnerRole = "TR0100";
        this.name1 = "";
        this.name2 = "";
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.city = "";
        this.district = "";
        this.regiogroup = "";
        this.postalCode = "";
        this.houseNo = "";
        this.street = "";
        this.country = "";
        this.contactPerName = "";
        this.panNumber = "";
        this.loanClass = "";
        this.financingType = "";
        this.debtEquityIndicator = "";
        this.projectCapaacity = 0.0;
        this.projectCapacityUnit="";
        this.projectState="";
        this.projectDistrict="";
        this.tenorYear=0;
        this.tenorMonth=0;
        this.projectCostInCrores=0.0;
        this.debtAmountInCrores=0.0;
        this.equityAmountInCrores=0.0;
        this.currency="";
        this.applicationCapitalInCrores=0.0;
        this.loanPurpose="";
        this.scheduledCommDate=LocalDate.now();
        this.groupCompanyName="";
        this.promoterName="";
        this.promoterNetWorthInCrores=0.0;
        this.promoterPATInCrores=0.0;
        this.promoterAreaOfBusiness="";
        this.promoterRating="";
        this.promoterKeyDirector="";
        this.loanStatus="";
        this.projectName="";
    }

    public String getLoanContract() {
        return loanContract;
    }

    public void setLoanContract(String loanContract) {
        this.loanContract = loanContract;
    }

    public String getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(String loanApplicationId) {
        loanApplicationId = loanApplicationId;
    }

    public String getBusPartnerNumber() {
        return busPartnerNumber;
    }

    public void setBusPartnerNumber(String busPartnerNumber) {
        this.busPartnerNumber = busPartnerNumber;
    }

    public String getPartnerCategory() {
        return partnerCategory;
    }

    public void setPartnerCategory(String partnerCategory) {
        this.partnerCategory = partnerCategory;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getPartnerGroup() {
        return partnerGroup;
    }

    public void setPartnerGroup(String partnerGroup) {
        this.partnerGroup = partnerGroup;
    }

    public String getPartnerExternalNumber() {
        return partnerExternalNumber;
    }

    public void setPartnerExternalNumber(String partnerExternalNumber) {
        this.partnerExternalNumber = partnerExternalNumber;
    }

    public String getPartnerRole() {
        return partnerRole;
    }

    public void setPartnerRole(String partnerRole) {
        this.partnerRole = partnerRole;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegiogroup() {
        return regiogroup;
    }

    public void setRegiogroup(String regiogroup) {
        this.regiogroup = regiogroup;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactPerName() {
        return contactPerName;
    }

    public void setContactPerName(String contactPerName) {
        this.contactPerName = contactPerName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getLoanClass() {
        return loanClass;
    }

    public void setLoanClass(String loanClass) {
        this.loanClass = loanClass;
    }

    public String getFinancingType() {
        return financingType;
    }

    public void setFinancingType(String financingType) {
        this.financingType = financingType;
    }

    public String getDebtEquityIndicator() {
        return debtEquityIndicator;
    }

    public void setDebtEquityIndicator(String debtEquityIndicator) {
        this.debtEquityIndicator = debtEquityIndicator;
    }

    public Double getProjectCapaacity() {
        return projectCapaacity;
    }

    public void setProjectCapaacity(Double projectCapaacity) {
        this.projectCapaacity = projectCapaacity;
    }

    public String getProjectCapacityUnit() {
        return projectCapacityUnit;
    }

    public void setProjectCapacityUnit(String projectCapacityUnit) {
        this.projectCapacityUnit = projectCapacityUnit;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectDistrict() {
        return projectDistrict;
    }

    public void setProjectDistrict(String projectDistrict) {
        this.projectDistrict = projectDistrict;
    }

    public Integer getTenorYear() {
        return tenorYear;
    }

    public void setTenorYear(Integer tenorYear) {
        this.tenorYear = tenorYear;
    }

    public Integer getTenorMonth() {
        return tenorMonth;
    }

    public void setTenorMonth(Integer tenorMonth) {
        this.tenorMonth = tenorMonth;
    }

    public Double getProjectCostInCrores() {
        return projectCostInCrores;
    }

    public void setProjectCostInCrores(Double projectCostInCrores) {
        this.projectCostInCrores = projectCostInCrores;
    }

    public Double getDebtAmountInCrores() {
        return debtAmountInCrores;
    }

    public void setDebtAmountInCrores(Double debtAmountInCrores) {
        this.debtAmountInCrores = debtAmountInCrores;
    }

    public Double getEquityAmountInCrores() {
        return equityAmountInCrores;
    }

    public void setEquityAmountInCrores(Double equityAmountInCrores) {
        this.equityAmountInCrores = equityAmountInCrores;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getApplicationCapitalInCrores() {
        return applicationCapitalInCrores;
    }

    public void setApplicationCapitalInCrores(Double applicationCapitalInCrores) {
        this.applicationCapitalInCrores = applicationCapitalInCrores;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public LocalDate getScheduledCommDate() {
        return scheduledCommDate;
    }

    public void setScheduledCommDate(LocalDate scheduledCommDate) {
        this.scheduledCommDate = scheduledCommDate;
    }

    public String getGroupCompanyName() {
        return groupCompanyName;
    }

    public void setGroupCompanyName(String groupCompanyName) {
        this.groupCompanyName = groupCompanyName;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public Double getPromoterNetWorthInCrores() {
        return promoterNetWorthInCrores;
    }

    public void setPromoterNetWorthInCrores(Double promoterNetWorthInCrores) {
        this.promoterNetWorthInCrores = promoterNetWorthInCrores;
    }

    public Double getPromoterPATInCrores() {
        return promoterPATInCrores;
    }

    public void setPromoterPATInCrores(Double promoterPATInCrores) {
        this.promoterPATInCrores = promoterPATInCrores;
    }

    public String getPromoterAreaOfBusiness() {
        return promoterAreaOfBusiness;
    }

    public void setPromoterAreaOfBusiness(String promoterAreaOfBusiness) {
        this.promoterAreaOfBusiness = promoterAreaOfBusiness;
    }

    public String getPromoterRating() {
        return promoterRating;
    }

    public void setPromoterRating(String promoterRating) {
        this.promoterRating = promoterRating;
    }

    public String getPromoterKeyDirector() {
        return promoterKeyDirector;
    }

    public void setPromoterKeyDirector(String promoterKeyDirector) {
        this.promoterKeyDirector = promoterKeyDirector;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "SAPLoanApplicationDetailsResource{" +
                "loanContract='" + loanContract + '\'' +
                ", LoanApplicationId='" + loanApplicationId + '\'' +
                ", busPartnerNumber='" + busPartnerNumber + '\'' +
                ", partnerCategory='" + partnerCategory + '\'' +
                ", partnerType='" + partnerType + '\'' +
                ", partnerGroup='" + partnerGroup + '\'' +
                ", partnerExternalNumber='" + partnerExternalNumber + '\'' +
                ", partnerRole='" + partnerRole + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", regiogroup='" + regiogroup + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", contactPerName='" + contactPerName + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", applicationDate=" + applicationDate +
                ", loanClass='" + loanClass + '\'' +
                ", financingType='" + financingType + '\'' +
                ", debtEquityIndicator='" + debtEquityIndicator + '\'' +
                ", projectCapaacity=" + projectCapaacity +
                ", projectCapacityUnit='" + projectCapacityUnit + '\'' +
                ", projectState='" + projectState + '\'' +
                ", projectDistrict='" + projectDistrict + '\'' +
                ", tenorYear=" + tenorYear +
                ", tenorMonth=" + tenorMonth +
                ", projectCostInCrores=" + projectCostInCrores +
                ", debtAmountInCrores=" + debtAmountInCrores +
                ", equityAmountInCrores=" + equityAmountInCrores +
                ", currency='" + currency + '\'' +
                ", applicationCapitalInCrores=" + applicationCapitalInCrores +
                ", loanPurpose='" + loanPurpose + '\'' +
                ", scheduledCommDate=" + scheduledCommDate +
                ", groupCompanyName='" + groupCompanyName + '\'' +
                ", promoterName='" + promoterName + '\'' +
                ", promoterNetWorthInCrores=" + promoterNetWorthInCrores +
                ", promoterPATInCrores=" + promoterPATInCrores +
                ", promoterAreaOfBusiness='" + promoterAreaOfBusiness + '\'' +
                ", promoterRating='" + promoterRating + '\'' +
                ", promoterKeyDirector='" + promoterKeyDirector + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
