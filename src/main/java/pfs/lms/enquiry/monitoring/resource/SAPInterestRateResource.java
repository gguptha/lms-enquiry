package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.RateOfInterest;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPInterestRateResource {

    @JsonProperty(value = "d")
    private SAPInterestRateResourceDetails sapInterestRateResourceDetails;


    public SAPInterestRateResourceDetails getSapInterestRateResourceDetails() {
        return sapInterestRateResourceDetails;
    }

    public void setSapInterestRateResourceDetails(SAPInterestRateResourceDetails sapInterestRateResourceDetails) {
        this.sapInterestRateResourceDetails = sapInterestRateResourceDetails;
    }

    public SAPInterestRateResourceDetails mapToSAP(RateOfInterest rateOfInterest) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPInterestRateResourceDetails detailedResource = new SAPInterestRateResourceDetails();

        detailedResource.setId(rateOfInterest.getId());
        detailedResource.setMonitorId(rateOfInterest.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(rateOfInterest.getSerialNumber() );

        detailedResource.setParticulars(rateOfInterest.getParticulars());
        detailedResource.setScheduled(rateOfInterest.getScheduledIfAny());
        detailedResource.setPrecodsanction( String.format("%.2f",rateOfInterest.getSanctionPreCod()));
        detailedResource.setPostcodsacnction(String.format("%.2f",rateOfInterest.getSanctionPreCod()));
        detailedResource.setPresentRoi(String.format("%.2f",rateOfInterest.getPresentRoi()));
        detailedResource.setFreeText(rateOfInterest.getFreeText());

        return detailedResource;


    }

}
