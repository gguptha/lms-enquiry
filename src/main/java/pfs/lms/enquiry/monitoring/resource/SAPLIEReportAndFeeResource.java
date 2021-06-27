package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFee;
import pfs.lms.enquiry.monitoring.lie.LendersIndependentEngineer;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class SAPLIEReportAndFeeResource implements Serializable   {


    public SAPLIEReportAndFeeResource() {
        saplieReportAndFeeDetailsResource = new SAPLIEReportAndFeeDetailsResource();
     }

    @JsonProperty(value = "d")
    private SAPLIEReportAndFeeDetailsResource saplieReportAndFeeDetailsResource;


    public SAPLIEReportAndFeeDetailsResource getSaplieReportAndFeeDetailsResource() {
        return saplieReportAndFeeDetailsResource;
    }

    public void setSaplieReportAndFeeDetailsResource(SAPLIEReportAndFeeDetailsResource saplieReportAndFeeDetailsResource) {
        this.saplieReportAndFeeDetailsResource = saplieReportAndFeeDetailsResource;
    }

    public SAPLIEReportAndFeeDetailsResource
                    mapToSAP(LIEReportAndFee lieReportAndFee,
                             byte[] documentContent,
                             User lastProcessedBy) throws ParseException {

        DataConversionUtility dataConversionUtility =  new DataConversionUtility();


       SAPLIEReportAndFeeDetailsResource detailsResource= new SAPLIEReportAndFeeDetailsResource();

        detailsResource.setId(lieReportAndFee.getId());
        detailsResource.setSerialNo(lieReportAndFee.getSerialNumber());
        detailsResource.setReporttype(lieReportAndFee.getReportType());
        detailsResource.setLieId(lieReportAndFee.getLendersIndependentEngineer().getId());

        if (lieReportAndFee.getDateOfReceipt() != null)
            detailsResource.setDateofreceipt(dataConversionUtility.convertDateToSAPFormat(lieReportAndFee.getDateOfReceipt()));
        else
            detailsResource.setDateofreceipt(null);

        if (lieReportAndFee.getInvoiceDate() != null)
            detailsResource.setInvoicedate(dataConversionUtility.convertDateToSAPFormat(lieReportAndFee.getInvoiceDate()));
        else
            detailsResource.setInvoicedate(null);

        detailsResource.setInvoiceno(lieReportAndFee.getInvoiceNo());
        detailsResource.setFeeamount(lieReportAndFee.getFeeAmount().toString());
        detailsResource.setStatusoffeepaid(lieReportAndFee.getStatusOfFeePaid());
 //                Sapfiinvoicedate
//                Sapfiinvoicenumber
//                Feeamountraisedoncustomer
//                Statusoffeereceipt


        if (lieReportAndFee.getNextReportDate() != null)
            detailsResource.setNextreportdate(dataConversionUtility.convertDateToSAPFormat(lieReportAndFee.getNextReportDate()));
        else
            detailsResource.setNextreportdate(null);

        //detailsResource.setDocumenttitle(lieReportAndFee.getDocumentTitle());

        detailsResource.setDocumenttitle("application/pdf");
        //detailsResource.setDocumentcontent(documentContent);
//        detailsResource.setFileName("File Name ");
//        detailsResource.setMimeType("application/pdf");


        return detailsResource;
    }
}
