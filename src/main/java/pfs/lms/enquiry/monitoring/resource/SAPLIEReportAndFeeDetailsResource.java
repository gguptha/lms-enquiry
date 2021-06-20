package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 * Created by gguptha on 09/11/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SAPLIEReportAndFeeDetailsResource {



    @JsonProperty (value = "Id")
    private String id;

    @JsonProperty (value = "LieId")
    private String lieId;

    @JsonProperty (value = "SerialNo")
    private Integer serialNo;

    @JsonProperty (value = "Reporttype")
    private String reporttype;

    @JsonProperty (value = "Dateofreceipt")
    private String dateofreceipt;

    @JsonProperty (value = "Invoicedate")
    private String invoicedate;

    @JsonProperty (value = "Invoiceno")
    private String invoiceno;

    @JsonProperty (value = "Feeamount")
    private String feeamount;

    @JsonProperty (value = "statusoffeepaid")
    private String statusoffeepaid;

    @JsonProperty (value = "Sapfiinvoicedate")
    private String Sapfiinvoicedate;

    @JsonProperty (value = "Sapfiinvoicenumber")
    private String Sapfiinvoicenumber;

    @JsonProperty (value = "Feeamountraisedoncustomer")
    private String Feeamountraisedoncustomer;

    @JsonProperty (value = "Statusoffeereceipt")
    private String Statusoffeereceipt;

    @Override
    public String toString() {
        return "SAPLIEReportAndFeeDetailsResource{" +
                "id='" + id + '\'' +
                ", lieId='" + lieId + '\'' +
                ", serialNo=" + serialNo +
                ", reporttype='" + reporttype + '\'' +
                ", dateofreceipt='" + dateofreceipt + '\'' +
                ", invoicedate='" + invoicedate + '\'' +
                ", invoiceno='" + invoiceno + '\'' +
                ", feeamount='" + feeamount + '\'' +
                ", statusoffeepaid='" + statusoffeepaid + '\'' +
                ", Sapfiinvoicedate='" + Sapfiinvoicedate + '\'' +
                ", Sapfiinvoicenumber='" + Sapfiinvoicenumber + '\'' +
                ", Feeamountraisedoncustomer='" + Feeamountraisedoncustomer + '\'' +
                ", Statusoffeereceipt='" + Statusoffeereceipt + '\'' +
                '}';
    }
}
