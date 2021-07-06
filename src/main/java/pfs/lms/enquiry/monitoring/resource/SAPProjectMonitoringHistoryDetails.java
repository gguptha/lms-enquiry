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
public class SAPProjectMonitoringHistoryDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "Serialno")
    private String serialNo;

    @JsonProperty(value = "Particulars")
    private String particulars;

    @JsonProperty(value = "Dateofentry")
    private String dateofentry;

    @JsonProperty(value = "Reviseddata1")
    private String reviseddata1;

    @JsonProperty(value = "Remarks")
    private Double remarks;


}
