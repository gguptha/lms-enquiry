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
        sapLoanMonitorDetailsResource = new SAPLoanMonitorDetailsResource();
    }

    @JsonProperty(value = "d")
    private SAPLoanMonitorDetailsResource sapLoanMonitorDetailsResource;

    public SAPLoanMonitorDetailsResource getSapLoanMonitorDetailsResource() {
        return sapLoanMonitorDetailsResource;
    }

    public void setSapLoanMonitorDetailsResource(SAPLoanMonitorDetailsResource sapLoanMonitorDetailsResource) {
        this.sapLoanMonitorDetailsResource = sapLoanMonitorDetailsResource;
    }


    public SAPLoanMonitorDetailsResource
                    mapLoanMonitorToSAP(LoanMonitor loanMonitor, Partner partner, User lastProcessedBy) throws ParseException {

        SAPLoanMonitorDetailsResource detailsResource = new SAPLoanMonitorDetailsResource();

       

        return detailsResource;
    }
}
