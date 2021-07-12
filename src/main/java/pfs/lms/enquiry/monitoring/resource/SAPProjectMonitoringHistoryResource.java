package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.monitoring.projectmonitoring.ProjectMonitoringDataItemHistory;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SAPProjectMonitoringHistoryResource {

    @JsonProperty(value = "d")
    private SAPProjectMonitoringHistoryResourceDetails sapProjectMonitoringHistoryResourceDetails;



    public SAPProjectMonitoringHistoryResourceDetails mapToSAP(ProjectMonitoringDataItemHistory projectMonitoringDataItemHistory,
                                                                String projectMonitoringDataId) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPProjectMonitoringHistoryResourceDetails detailedResource = new SAPProjectMonitoringHistoryResourceDetails();


        detailedResource.setId(projectMonitoringDataItemHistory.getId().toString());
        detailedResource.setProjectMonDataHdrId(projectMonitoringDataId);

        // TODO
        //detailedResource.setSerialNo(projectMonitoringDataItemHistory.getSerialNo());

        detailedResource.setParticulars(projectMonitoringDataItemHistory.getParticulars());
        if (projectMonitoringDataItemHistory.getDateOfEntry() != null)
            detailedResource.setDateofentry(dataConversionUtility.convertDateToSAPFormat(projectMonitoringDataItemHistory.getDateOfEntry()));
        else
            detailedResource.setDateofentry(null);
        detailedResource.setReviseddata1(projectMonitoringDataItemHistory.getRevisedData1());
        detailedResource.setReviseddata2(projectMonitoringDataItemHistory.getRevisedData2());
        detailedResource.setRemarks(projectMonitoringDataItemHistory.getRemarks());


        return detailedResource;


    }

}
