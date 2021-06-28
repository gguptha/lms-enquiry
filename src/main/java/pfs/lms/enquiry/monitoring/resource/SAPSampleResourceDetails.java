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
public class SAPSampleResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "SerialNo")
    private String serialNo;

    @JsonProperty(value = "Fiscalyear")
    private String fiscalyear;

    @JsonProperty(value = "Turnover")
    private Double turnover;

    @JsonProperty(value = "Pat")
    private Double pat;

    @JsonProperty(value = "Networth")
    private Double networth;

    @JsonProperty(value = "Dateofexternalrating")
    private String dateofexternalrating;

    @JsonProperty(value = "Nextduedateofexternalrating")
    private String nextduedateofexternalrating;

    @JsonProperty(value = "Overallrating")
    private String overallrating;


    @Override
    public String toString() {
        return "SAPBorrowerFinancialsResourceDetails{" +
                "id='" + id + '\'' +
                ", monitorId='" + monitorId + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", fiscalyear='" + fiscalyear + '\'' +
                ", turnover='" + turnover + '\'' +
                ", pat='" + pat + '\'' +
                ", networth='" + networth + '\'' +
                ", dateofexternalrating='" + dateofexternalrating + '\'' +
                ", nextduedateofexternalrating='" + nextduedateofexternalrating + '\'' +
                ", overallrating='" + overallrating + '\'' +
                '}';
    }
}
