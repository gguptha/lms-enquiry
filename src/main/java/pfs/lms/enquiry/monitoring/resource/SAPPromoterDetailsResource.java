package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.PromoterDetails;
import pfs.lms.enquiry.domain.PromoterDetailsItem;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Component
public class SAPPromoterDetailsResource {


    @JsonProperty(value = "d")
    private SAPPromoterDetailsResourceDetails  sapPromoterDetailsResourceDetails;


    public SAPPromoterDetailsResourceDetails getSapPromoterDetailsResourceDetails() {
        return sapPromoterDetailsResourceDetails;
    }

    public void setSapPromoterDetailsResourceDetails(SAPPromoterDetailsResourceDetails sapPromoterDetailsResourceDetails) {
        this.sapPromoterDetailsResourceDetails = sapPromoterDetailsResourceDetails;
    }

    public SAPPromoterDetailsResourceDetails mapToSAP(PromoterDetails promoterDetails) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPPromoterDetailsResourceDetails detailedResource = new SAPPromoterDetailsResourceDetails();
        detailedResource.setId(promoterDetails.getId());
        detailedResource.setMonitorId(promoterDetails.getLoanMonitor().getId().toString());

        if (promoterDetails.getDateOfChange() != null) {
            detailedResource.setDateofchange(dataConversionUtility.convertDateToSAPFormat(promoterDetails.getDateOfChange()));
        } else
            detailedResource.setDateofchange(null);
        detailedResource.setGroupexposure(promoterDetails.getGroupExposure());

        for (PromoterDetailsItem promoterDetailsItem : promoterDetails.getPromoterDetailsItemSet()) {
            SAPPromoterDetailsItemsResourceDetails sapPromoterDetailsItemsResourceDetails = new SAPPromoterDetailsItemsResourceDetails();
            sapPromoterDetailsItemsResourceDetails.setId(promoterDetailsItem.getId());
            sapPromoterDetailsItemsResourceDetails.setSerialNo(promoterDetailsItem.getSerialNumber());
            sapPromoterDetailsItemsResourceDetails.setPromDtlId(promoterDetails.getId());
            sapPromoterDetailsItemsResourceDetails.setSanctionedequitylinkinstrument(promoterDetailsItem.getEquityLinkInstrumentSanction());
            sapPromoterDetailsItemsResourceDetails.setCurrentequitylinkinstrument(promoterDetailsItem.getEquityLinkInstrumentCurrent());
            sapPromoterDetailsItemsResourceDetails.setCurrentpaidupce(promoterDetailsItem.getPaidupCapitalEquityCurrent());
            sapPromoterDetailsItemsResourceDetails.setSanctionedpaidupce(promoterDetailsItem.getPaidupCapitalEquitySanction());
            //sapPromoterDetailsItemsResourceDetails.setBupaId(promoterDetailsItem.se);
            detailedResource.add(sapPromoterDetailsItemsResourceDetails);
        }

        return detailedResource;
    }





}
