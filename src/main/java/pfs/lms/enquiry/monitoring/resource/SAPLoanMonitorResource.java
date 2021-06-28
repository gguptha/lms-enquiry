package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;

import java.io.Serializable;
import java.text.ParseException;

@Component
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class SAPLoanMonitorResource implements Serializable   {



    public SAPLoanMonitorResource() {
        sapLoanMonitorResourceDetails = new SAPLoanMonitorResourceDetails();
    }

    @JsonProperty(value = "d")
    private SAPLoanMonitorResourceDetails sapLoanMonitorResourceDetails;

    public SAPLoanMonitorResourceDetails getSapLoanMonitorResourceDetails() {
        return sapLoanMonitorResourceDetails;
    }

    public void setSapLoanMonitorResourceDetails(SAPLoanMonitorResourceDetails sapLoanMonitorResourceDetails) {
        this.sapLoanMonitorResourceDetails = sapLoanMonitorResourceDetails;
    }


    public SAPLoanMonitorResourceDetails
                    mapLoanMonitorToSAP(LoanMonitor loanMonitor, Partner partner, User lastProcessedBy) throws ParseException {

        SAPLoanMonitorResourceDetails detailsResource = new SAPLoanMonitorResourceDetails();

       

        return detailsResource;
    }
}
