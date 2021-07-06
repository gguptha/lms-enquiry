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

public class SAPProjectMonitoringResource {

    @JsonProperty(value = "d")
    private SAPProjectMonitoringResourceDetails sapProjectMonitoringResourceDetails;

    public SAPProjectMonitoringResourceDetails getSapProjectMonitoringResourceDetails() {
        return sapProjectMonitoringResourceDetails;
    }

    public void setSapProjectMonitoringResourceDetails(SAPProjectMonitoringResourceDetails sapProjectMonitoringResourceDetails) {
        this.sapProjectMonitoringResourceDetails = sapProjectMonitoringResourceDetails;
    }
//
//    public SAPProjectMonitoringResourceDetails mapToSAP(ProjectMonitor borrowerFinancials) throws ParseException {
//
//        DataConversionUtility dataConversionUtility = new DataConversionUtility();
//
//        SAPProjectMonitoringResourceDetails detailedResource = new SAPProjectMonitoringResourceDetails();
//        detailedResource.setId(securityCompliance.getId());
//        detailedResource.setMonitorId(securityCompliance.getLoanMonitor().getId().toString());
//        detailedResource.setSerialNo(securityCompliance.getSerialNumber().toString());
//
//        return detailedResource;
//
//
//    }

}
