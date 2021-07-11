package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SAPProjectMonitoringResourceDataItemDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "ProjectMonDataHdrId")
    private String projectMonDataHdrId;

    @JsonProperty(value = "Serialno")
    private Integer serialNo;

    @JsonProperty(value = "Particulars")
    private String Particulars;

    @JsonProperty(value = "Dateofentry")
    private String dateofentry;

    @JsonProperty(value = "Reviseddata1")
    private String reviseddata1;

    @JsonProperty(value = "Reviseddata2")
    private String reviseddata2;

    @JsonProperty(value = "Remarks")
    private String remarks;




}
