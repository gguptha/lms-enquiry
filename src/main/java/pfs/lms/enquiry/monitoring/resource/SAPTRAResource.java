package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.tra.TrustRetentionAccount;
import pfs.lms.enquiry.utils.DataConversionUtility;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Component
public class SAPTRAResource {

    private SAPTRAResourceDetails saptraResourceDetails;

    public SAPTRAResourceDetails getSaptraResourceDetails() {
        return saptraResourceDetails;
    }

    public void setSaptraResourceDetails(SAPTRAResourceDetails saptraResourceDetails) {
        this.saptraResourceDetails = saptraResourceDetails;
    }

    public SAPTRAResourceDetails mapToSAP(TrustRetentionAccount trustRetentionAccount) {
        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPTRAResourceDetails detailedResource = new SAPTRAResourceDetails();

        detailedResource.setId(trustRetentionAccount.getId());
        detailedResource.setMonitorId(trustRetentionAccount.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(trustRetentionAccount.getSerialNumber());

        detailedResource.setBankkey(trustRetentionAccount.getBankKey());
        detailedResource.setTrabankname(trustRetentionAccount.getTraBankName());
        detailedResource.setBranch(trustRetentionAccount.getBranch());
        detailedResource.setAdddress(trustRetentionAccount.getAddress());
        detailedResource.setBeneficiaryname(trustRetentionAccount.getBeneficiaryName());
        detailedResource.setIfsccode(trustRetentionAccount.getIfscCode());
        detailedResource.setAccountnumber(trustRetentionAccount.getAccountNumber());
        detailedResource.setContactname(trustRetentionAccount.getContactName());
        detailedResource.setTypeofaccount(trustRetentionAccount.getTypeOfAccount());
        detailedResource.setContactnumber(trustRetentionAccount.getContactNumber());
        detailedResource.setEmailid(trustRetentionAccount.getEmail());
        detailedResource.setPfsauthorisedpersonbpcode(trustRetentionAccount.getPfsAuthorisedPersonBPCode());
        detailedResource.setPfsauthorisedperson(trustRetentionAccount.getPfsAuthorisedPerson());

        return detailedResource;

    }
}
