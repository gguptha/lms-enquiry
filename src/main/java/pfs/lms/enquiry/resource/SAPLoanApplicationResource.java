package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class SAPLoanApplicationResource implements Serializable {

    public SAPLoanApplicationResource() {
        sapLoanApplicationDetailsResource = new SAPLoanApplicationDetailsResource();
    }

    @JsonProperty(value = "d")
    private SAPLoanApplicationDetailsResource sapLoanApplicationDetailsResource;

    public SAPLoanApplicationDetailsResource getSapLoanApplicationDetailsResource() {
        return sapLoanApplicationDetailsResource;
    }

    public void setSapLoanApplicationDetailsResource(SAPLoanApplicationDetailsResource sapLoanApplicationDetailsResource) {
        this.sapLoanApplicationDetailsResource = sapLoanApplicationDetailsResource;
    }
}