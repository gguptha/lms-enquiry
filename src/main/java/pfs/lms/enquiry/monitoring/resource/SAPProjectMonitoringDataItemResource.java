package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.projectmonitoring.ProjectMonitoringData;
import pfs.lms.enquiry.monitoring.projectmonitoring.ProjectMonitoringDataItem;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SAPProjectMonitoringDataItemResource {

    @JsonProperty(value = "d")
    private SAPProjectMonitoringResourceDataItemDetails sapProjectMonitoringResourceDataItemDetails;

    public SAPProjectMonitoringResourceDataItemDetails getSapProjectMonitoringResourceDataItemDetails() {
        return sapProjectMonitoringResourceDataItemDetails;
    }

    public void setSapProjectMonitoringResourceDataItemDetails(SAPProjectMonitoringResourceDataItemDetails sapProjectMonitoringResourceDataItemDetails) {
        this.sapProjectMonitoringResourceDataItemDetails = sapProjectMonitoringResourceDataItemDetails;
    }

    public SAPProjectMonitoringResourceDataItemDetails mapToSAP(ProjectMonitoringDataItem projectMonitoringDataItem,
                                                                String projectMonitoringDataId) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPProjectMonitoringResourceDataItemDetails detailedResource = new SAPProjectMonitoringResourceDataItemDetails();

        detailedResource.setId(projectMonitoringDataItem.getId().toString());
        detailedResource.setProjectMonDataHdrId(projectMonitoringDataId);

        detailedResource.setSerialNo(projectMonitoringDataItem.getSerialNumber());
        detailedResource.setParticulars(projectMonitoringDataItem.getParticulars());
        if (projectMonitoringDataItem.getDateOfEntry() != null)
            detailedResource.setDateofentry(dataConversionUtility.convertDateToSAPFormat(projectMonitoringDataItem.getDateOfEntry()));
        else
            detailedResource.setDateofentry(null);
        detailedResource.setReviseddata1(projectMonitoringDataItem.getRevisedData1());
        detailedResource.setReviseddata2(projectMonitoringDataItem.getRevisedData2());
        detailedResource.setRemarks(projectMonitoringDataItem.getRemarks());




        return detailedResource;


    }

}
