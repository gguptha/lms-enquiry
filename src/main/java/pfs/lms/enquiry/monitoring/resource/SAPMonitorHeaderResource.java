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
public class SAPMonitorHeaderResource implements Serializable   {



    public SAPMonitorHeaderResource() {
        sapMonitorHeaderResourceDetails = new SAPMonitorHeaderResourceDetails();
    }

    @JsonProperty(value = "d")
    private SAPMonitorHeaderResourceDetails sapMonitorHeaderResourceDetails;



    public void setSapMonitorHeaderResourceDetails (SAPMonitorHeaderResourceDetails sapMonitorHeaderResourceDetails) {
        this.sapMonitorHeaderResourceDetails = sapMonitorHeaderResourceDetails;
    }


    public SAPMonitorHeaderResourceDetails
                    mapLoanMonitorToSAP(LoanMonitor loanMonitor ) throws ParseException {

        SAPMonitorHeaderResourceDetails detailsResource = new SAPMonitorHeaderResourceDetails();

        detailsResource.setLoanContract(loanMonitor.getLoanApplication().getLoanContractId());
        detailsResource.setMonitorId(loanMonitor.getId().toString());
        detailsResource.setProcessInstanceId(loanMonitor.getProcessInstanceId());
        if (loanMonitor.getWorkFlowStatusCode() != null)
            detailsResource.setWorkflowStatusCode(loanMonitor.getWorkFlowStatusCode().toString());
        else
            detailsResource.setWorkflowStatusCode(null);
        if (loanMonitor.getWorkFlowStatusDescription() != null)
            detailsResource.setWorkflowStatusDesc(loanMonitor.getWorkFlowStatusDescription());
        else
            detailsResource.setWorkflowStatusCode(null);

        return detailsResource;
    }
}
