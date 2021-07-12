package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.projectmonitoring.ProjectMonitoringData;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SAPProjectMonitoringDataResource {

    @JsonProperty(value = "d")
    private SAPProjectMonitoringResourceDataDetails sapProjectMonitoringResourceDataDetails;

    public SAPProjectMonitoringResourceDataDetails getSapProjectMonitoringResourceDataDetails() {
        return sapProjectMonitoringResourceDataDetails;
    }

    public void setSapProjectMonitoringResourceDataDetails(SAPProjectMonitoringResourceDataDetails sapProjectMonitoringResourceDataDetails) {
        this.sapProjectMonitoringResourceDataDetails = sapProjectMonitoringResourceDataDetails;
    }

    public SAPProjectMonitoringResourceDataDetails mapToSAP(ProjectMonitoringData projectMonitoringData) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPProjectMonitoringResourceDataDetails detailedResource = new SAPProjectMonitoringResourceDataDetails();

        detailedResource.setId(projectMonitoringData.getId().toString());
        detailedResource.setMonitorId(projectMonitoringData.getLoanMonitor().getId().toString());

        if(projectMonitoringData.getDateOfChange() != null) {
            String date = dataConversionUtility.convertDateToSAPFormat(projectMonitoringData.getDateOfChange());
            detailedResource.setCreateDate(date);
        } else
        detailedResource.setCreateDate(null);

        return detailedResource;


    }

}
