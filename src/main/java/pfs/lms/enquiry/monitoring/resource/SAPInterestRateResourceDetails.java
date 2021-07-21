package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pfs.lms.enquiry.domain.ConditionType;
import pfs.lms.enquiry.domain.InterestCalculationMethod;
import pfs.lms.enquiry.domain.PaymentForm;
import pfs.lms.enquiry.domain.ReferenceInterestSign;

import java.util.Date;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SAPInterestRateResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty (value = "SerialNo")
    private Integer serialNo;

    @JsonProperty(value="MonitorId")
    private String monitorId;

    @JsonProperty(value = "ConditionType")
    private String conditionType;

    @JsonProperty(value = "ValidFromDate")
    private String validFromDate;


    // 0 - Fixed
    // 1 - Reference Int. Rate
    @JsonProperty(value = "InterestTypeIndicator")
    private Character interestTypeIndicator;

    @JsonProperty(value = "ReferenceInterestType")
    private String referenceInterestRate;

    // +  - Plus
    // -  - Minus
    // *  - Multiply
    @JsonProperty(value = "ReferenceInterestSign")
    private String refInterestSign;

    @JsonProperty(value = "InterestRatePct")
    private String interestRate;

    @JsonProperty(value = "CalculationDate")
    private String calculationDate;

    @JsonProperty(value = "IsCalculationDateOnMonthEnd")
    private Character isCalculationDateOnMonthEnd;

    @JsonProperty(value = "DueDate")
    private String dueDate;

    @JsonProperty(value = "IsDueDateOnMonthEnd")
    private Character isDueDateOnMonthEnd;

    @JsonProperty(value = "InterestPaymentFrequency")
    private String interestPaymentFrequency;

    @JsonProperty(value = "PaymentForm")
    private String paymentForm;

    @JsonProperty(value = "InterestCalculationMethod")
    private String interestCalculationMethod;


}
