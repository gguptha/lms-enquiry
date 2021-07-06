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
public class SAPOperatingParameterResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "SerialNo")
    private Integer serialNo;

    @JsonProperty(value = "MonthYear")
    private String monthYear;

    @JsonProperty(value = "ExportUnit")
    private String exportUnit;

    @JsonProperty(value = "PlfPercent")
    private String plfPercent;

    @JsonProperty(value = "AppliTariff")
    private String appliTariff;

    @JsonProperty(value = "Revenue")
    private String revenue;

    @JsonProperty(value = "InoviceDate")
    private String inoviceDate;

    @JsonProperty(value = "DatePayment")
    private String datePayment;


    @JsonProperty(value = "Co2Emission")
    private String co2Emission;

    @JsonProperty(value = "WaterSaved")
    private String waterSaved;

    @JsonProperty(value = "FileType")
    private String fileType;



    @JsonProperty(value = "ReceivedDate")
    private String receivedDate;

//    @JsonProperty(value = "Overallrating")
//    private String overallrating;


    @JsonProperty(value = "AvgRealisationPeriod")
    private String avgRealisationPeriod;

}
