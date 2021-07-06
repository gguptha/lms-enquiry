package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sajeev on 28-Jun-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class SAPTRAResourceDetails {

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "MonitorId")
    private String monitorId;

    @JsonProperty(value = "SerialNo")
    private Integer serialNo;

    @JsonProperty(value = "Bankkey")
    private String  bankkey;
    
    @JsonProperty(value = "Trabankname")
    private String trabankname;
    @JsonProperty(value = "Branch")
    private String branch;

    @JsonProperty(value = "Address")
    private String adddress;
    @JsonProperty(value = "Beneficiaryname")
    private String beneficiaryname;
    @JsonProperty(value = "Ifsccode")
    private String ifsccode;
    @JsonProperty(value = "Accountnumber")
    private String accountnumber;

    @JsonProperty(value = "Contactname")
    private String contactname;
    @JsonProperty(value = "Typeofaccount")
    private String typeofaccount;
    @JsonProperty(value = "Contactnumber")
    private String contactnumber;
    @JsonProperty(value = "Emailid")
    private String emailid;

    @JsonProperty(value = "Pfsauthorisedpersonbpcode")
    private String pfsauthorisedpersonbpcode;
    @JsonProperty(value = "Pfsauthorisedperson")
    private String pfsauthorisedperson;






}
