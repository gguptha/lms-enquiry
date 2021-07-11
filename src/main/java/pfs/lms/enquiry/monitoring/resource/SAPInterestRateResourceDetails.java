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
public class SAPInterestRateResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "SerialNo")
    private Integer serialNo;

    @JsonProperty(value = "Particulars")
    private String particulars;

    @JsonProperty(value = "Scheduled")
    private String scheduled;

    @JsonProperty(value = "Precodsanction")
    private String  precodsanction;

    @JsonProperty(value = "Postcodsacnction")
    private String postcodsacnction;

    @JsonProperty(value = "PresentRoi")
    private String presentRoi;

    @JsonProperty(value = "FreeText")
    private String freeText;


}
