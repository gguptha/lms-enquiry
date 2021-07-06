package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.SecurityCompliance;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.repository.SecurityComplianceRepository;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPSecurityComplianceResource {

    @JsonProperty(value = "d")
    private SAPSecurityComplianceResourceDetails sapSecurityComplianceResourceDetails;

    public SAPSecurityComplianceResourceDetails getSapSecurityComplianceResourceDetails() {
        return sapSecurityComplianceResourceDetails;
    }

    public void setSapSecurityComplianceResourceDetails(SAPSecurityComplianceResourceDetails sapSecurityComplianceResourceDetails) {
        this.sapSecurityComplianceResourceDetails = sapSecurityComplianceResourceDetails;
    }

    public SAPSecurityComplianceResourceDetails mapToSAP(SecurityCompliance securityCompliance) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPSecurityComplianceResourceDetails detailedResource = new SAPSecurityComplianceResourceDetails();

        detailedResource.setId(securityCompliance.getId());
        detailedResource.setMonitorId(securityCompliance.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(securityCompliance.getSerialNumber() );
        if (securityCompliance.getCollateralObjectType() != null)
        detailedResource.setZcollateralType(securityCompliance.getCollateralObjectType().toString());

//        detailedResource.setZapplEryDisbur(securityCompliance.get());
//        detailedResource.setZresponParty(securityCompliance.get());
          detailedResource.setZConditionDesc("Collateral Condition");

          //detailedResource.setZConditionDesc(securityCompliance.);
       // securityCompliance.getD

        detailedResource.setZsecNoOfUnits(securityCompliance.getSecurityNoOfUnits().toString());
        detailedResource.setZsecValue( String.format("%.2f", securityCompliance.getSecurityFaceValueAmount()));
        detailedResource.setZsecPctHolding(String.format("%.2f", securityCompliance.getHoldingPercentage()));

        detailedResource.setZcolTimelinesText(securityCompliance.getTimelines());
        detailedResource.setZcolSecType(securityCompliance.getCollateralAgreementType());
        detailedResource.setZcolActDaysPrfx(securityCompliance.getActionPeriodPrefix());
        detailedResource.setZcolActDaysSuffix(securityCompliance.getActionPeriodSuffix());
        if (securityCompliance.getPeriodNumber() != null)
        detailedResource.setZcolActDays(securityCompliance.getPeriodNumber().toString());
        detailedResource.setZcolEvent(securityCompliance.getEventType());


        if(securityCompliance.getEventDate() != null) {
            detailedResource.setZcolEventDate(dataConversionUtility.convertDateToSAPFormat(securityCompliance.getEventDate()));
        } else
            detailedResource.setZcolEventDate(null);

        if(securityCompliance.getDateOfCreation() != null) {
            detailedResource.setZcolTimelineDate(dataConversionUtility.convertDateToSAPFormat(securityCompliance.getDateOfCreation()));
        } else
            detailedResource.setZcolTimelineDate(null);

        detailedResource.setZcolValPeriod(securityCompliance.getValidityPeriod());

        if(securityCompliance.getSecurityPerfectionDate() != null) {
            detailedResource.setZcolSecPerDate(dataConversionUtility.convertDateToSAPFormat(securityCompliance.getSecurityPerfectionDate()));
        } else
            detailedResource.setZcolSecPerDate(null);

        detailedResource.setZcolExpValue(String.format("%.2f", securityCompliance.getValue()) );
        detailedResource.setZcolRemarks(securityCompliance.getRemarks());
        detailedResource.setZcolLocation(securityCompliance.getLocation());
        detailedResource.setZcolAddtlText(securityCompliance.getAdditionalText());
        detailedResource.setZreArea(String.format("%.2f",  securityCompliance.getRealEstateLandArea()));
        detailedResource.setZreAreaUom(securityCompliance.getAreaUnitOfMeasure());

        return detailedResource;


    }

}
