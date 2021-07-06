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
public class SAPPromoterDetailsItemResource {


    @JsonProperty(value = "d")
    private SAPPromoterDetailsItemsResourceDetails sapPromoterDetailsItemsResourceDetails;

    public SAPPromoterDetailsItemsResourceDetails getSapPromoterDetailsItemsResourceDetails() {
        return sapPromoterDetailsItemsResourceDetails;
    }

    public void setSapPromoterDetailsItemsResourceDetails(SAPPromoterDetailsItemsResourceDetails sapPromoterDetailsItemsResourceDetails) {
        this.sapPromoterDetailsItemsResourceDetails = sapPromoterDetailsItemsResourceDetails;
    }

    public SAPPromoterDetailsItemsResourceDetails mapToSAP(PromoterDetailsItem promoterDetailsItem) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPPromoterDetailsItemsResourceDetails detailedResource = new SAPPromoterDetailsItemsResourceDetails();
        detailedResource.setId(promoterDetailsItem.getId());

        //detailedResource.setMonitorId(promoterDetailsItem.get);


        detailedResource.setSerialNo(promoterDetailsItem.getSerialNumber() );
        detailedResource.setShareholdingcompany(promoterDetailsItem.getShareHoldingCompany());
        detailedResource.setCurrentpaidupce(promoterDetailsItem.getPaidupCapitalEquityCurrent());
        detailedResource.setCurrentequitylinkinstrument(promoterDetailsItem.getPaidupCapitalEquityCurrent());
        detailedResource.setSanctionedequitylinkinstrument(promoterDetailsItem.getEquityLinkInstrumentSanction());
        detailedResource.setCurrentequitylinkinstrument(promoterDetailsItem.getEquityLinkInstrumentCurrent());

        return detailedResource;
    }





}
