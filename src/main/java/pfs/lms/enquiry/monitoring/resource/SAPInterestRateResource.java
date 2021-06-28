package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import pfs.lms.enquiry.domain.RateOfInterest;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
public class SAPInterestRateResource {

    @JsonProperty(value = "d")
    private SAPInterestRateResourceDetails sapInterestRateResourceDetails;

    public SAPInterestRateResource(SAPInterestRateResourceDetails sapInterestRateResourceDetails) {
        this.sapInterestRateResourceDetails = sapInterestRateResourceDetails;
    }



    public SAPInterestRateResourceDetails mapToSAP(RateOfInterest rateOfInterest) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPInterestRateResourceDetails detailedResource = new SAPInterestRateResourceDetails();

        detailedResource.setId(rateOfInterest.getId());
        detailedResource.setMonitorId(rateOfInterest.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(rateOfInterest.getSerialNumber().toString());

        detailedResource.setParticulars(rateOfInterest.getParticulars());
        detailedResource.setScheduled(rateOfInterest.getScheduledIfAny());
        detailedResource.setPrecodsanction(rateOfInterest.getSanctionPreCod());
        detailedResource.setPostcodsacnction(rateOfInterest.getSanctionPreCod());
        detailedResource.setPresentRoi(rateOfInterest.getPresentRoi());
        detailedResource.setFreeText(rateOfInterest.getFreeText());

        return detailedResource;


    }

}
