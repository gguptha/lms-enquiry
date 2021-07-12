package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPProjectMonitoringHistoryResource {

    @JsonProperty(value = "d")
    private SAPProjectMonitoringResourceDataItemDetails sapProjectMonitoringResourceDataItemDetails;

    public SAPProjectMonitoringResourceDataItemDetails getSapProjectMonitoringResourceDetails() {
        return sapProjectMonitoringResourceDataItemDetails;
    }

    public void setSapProjectMonitoringResourceDetails(SAPProjectMonitoringResourceDataItemDetails sapProjectMonitoringResourceDataItemDetails) {
        this.sapProjectMonitoringResourceDataItemDetails = sapProjectMonitoringResourceDataItemDetails;
    }

    public SAPProjectMonitoringResourceDataItemDetails mapToSAP(BorrowerFinancials borrowerFinancials) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPProjectMonitoringResourceDataItemDetails detailedResource = new SAPProjectMonitoringResourceDataItemDetails();
//
//        detailedResource.setId(borrowerFinancials.getId());
//        detailedResource.setMonitorId(borrowerFinancials.getLoanMonitor().getId().toString());
//        detailedResource.setSerialNo(borrowerFinancials.getSerialNumber().toString());
//
//        if (borrowerFinancials.getDateOfExternalRating() != null)
//             detailedResource.setDateofexternalrating(dataConversionUtility.convertDateToSAPFormat(borrowerFinancials.getDateOfExternalRating()));
//        else
//            detailedResource.setDateofexternalrating(null);
//
//        if(borrowerFinancials.getNextDueDateOfExternalRating() != null) {
//            detailedResource.setNextduedateofexternalrating(dataConversionUtility.convertDateToSAPFormat(borrowerFinancials.getNextDueDateOfExternalRating()));
//        } else
//            detailedResource.setNextduedateofexternalrating(null);
//
//        detailedResource.setFiscalyear(borrowerFinancials.getFiscalYear().toString());
//        detailedResource.setNetworth(borrowerFinancials.getNetWorth());
//        detailedResource.setTurnover(borrowerFinancials.getTurnover());
//        detailedResource.setPat(borrowerFinancials.getPat());
//        detailedResource.setOverallrating(borrowerFinancials.getOverAllRating());
        

        return detailedResource;


    }

}
