package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.monitoring.promoterFinancials.PromoterFinancials;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPPromoterFinancialsResource {

    @JsonProperty(value = "d")
    private SAPPromoterFinancialResourceDetails sapPromoterFinancialResourceDetails;


    public SAPPromoterFinancialResourceDetails getSapPromoterFinancialResourceDetails() {
        return sapPromoterFinancialResourceDetails;
    }

    public void setSapPromoterFinancialResourceDetails(SAPPromoterFinancialResourceDetails sapPromoterFinancialResourceDetails) {
        this.sapPromoterFinancialResourceDetails = sapPromoterFinancialResourceDetails;
    }

    public SAPPromoterFinancialResourceDetails mapToSAP(PromoterFinancials promoterFinancials) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPPromoterFinancialResourceDetails detailedResource = new SAPPromoterFinancialResourceDetails();

        detailedResource.setId(promoterFinancials.getId());
        detailedResource.setMonitorId(promoterFinancials.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(promoterFinancials.getSerialNumber() );

        if (promoterFinancials.getDateOfExternalRating() != null)
             detailedResource.setDateofexternalrating(dataConversionUtility.convertDateToSAPFormat(promoterFinancials.getDateOfExternalRating()));
        else
            detailedResource.setDateofexternalrating(null);

        if(promoterFinancials.getNextDueDateOfExternalRating() != null) {
            detailedResource.setNextduedateofexternalrating(dataConversionUtility.convertDateToSAPFormat(promoterFinancials.getNextDueDateOfExternalRating()));
        } else
            detailedResource.setNextduedateofexternalrating(null);

        detailedResource.setFiscalyear(promoterFinancials.getFiscalYear().toString());
        detailedResource.setNetworth(promoterFinancials.getNetWorth());
        detailedResource.setTurnover(promoterFinancials.getTurnover());
        detailedResource.setPat(promoterFinancials.getPat());
        detailedResource.setOverallrating(promoterFinancials.getOverAllRating());
        

        return detailedResource;


    }

}
