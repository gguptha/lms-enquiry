package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SAPSecurityComplianceResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "SerialNo")
    private Integer serialNo;

    @JsonProperty(value = "ZcollateralType")
    private String zcollateralType;

    @JsonProperty(value = "ZConditionDesc")
    private String zConditionDesc;


    @JsonProperty(value = "ZapplEryDisbur")
    private Boolean zapplEryDisbur;

    @JsonProperty(value = "ZresponParty")
    private String zresponParty;

    @JsonProperty(value = "ZcondCat")
    private String  zcondCat;

    @JsonProperty(value = "ZmasterCond")
    private String  zmasterCond;

    @JsonProperty(value = "ZsecNoOfUnits")
    private String  zsecNoOfUnits;

    @JsonProperty(value = "ZsecValue")
    private String zsecValue;

    @JsonProperty(value = "ZsecValueCurr")
    private String zsecValueCurr;

    @JsonProperty(value = "ZsecPctHolding")
    private String zsecPctHolding  ;

    @JsonProperty(value = "ZcolSecType")
    private String  zcolSecType;

    @JsonProperty(value = "ZcolTimelinesText")
    private String zcolTimelinesText;

    @JsonProperty(value = "ZcolActDaysPrfx")
    private String  zcolActDaysPrfx;

    @JsonProperty(value = "ZcolActDaysSuffix")
    private String  zcolActDaysSuffix;

    @JsonProperty(value = "ZcolActDays")
    private String  zcolActDays;

    @JsonProperty(value = "ZcolEvent")
    private String  zcolEvent;

    @JsonProperty(value = "ZcolEventDate")
    private String  zcolEventDate;

    @JsonProperty(value = "ZcolTimelineDate")
    private String zcolTimelineDate ;

    @JsonProperty(value = "ZcolValPeriod")
    private String zcolValPeriod ;


    @JsonProperty(value = "ZcolExpValue")
    private String  zcolExpValue;

    @JsonProperty(value = "ZcolSecPerDate")
    private String  zcolSecPerDate;

    @JsonProperty(value = "ZcolRemarks")
    private String  zcolRemarks;

    @JsonProperty(value = "ZcolLocation")
    private String  zcolLocation;

    @JsonProperty(value = "ZcolAddtlText")
    private String zcolAddtlText;

    @JsonProperty(value = "ZreArea")
    private String zreArea  ;


    @JsonProperty(value = "ZreAreaUom")
    private String zreAreaUom  ;


}
