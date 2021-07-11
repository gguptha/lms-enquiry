package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.monitoring.operatingparameters.OperatingParameter;
import pfs.lms.enquiry.monitoring.operatingparameters.OperatingParameterPLFResource;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sajeev on 28-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPOperatingParameterResource {

    @JsonProperty(value = "d")
    private SAPOperatingParameterResourceDetails sapOperatingParameterResourceDetails;


    public SAPOperatingParameterResourceDetails getSapOperatingParameterResourceDetails() {
        return sapOperatingParameterResourceDetails;
    }

    public void setSapOperatingParameterResourceDetails(SAPOperatingParameterResourceDetails sapOperatingParameterResourceDetails) {
        this.sapOperatingParameterResourceDetails = sapOperatingParameterResourceDetails;
    }

    public SAPOperatingParameterResourceDetails mapToSAP(OperatingParameter operatingParameter) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPOperatingParameterResourceDetails detailedResource = new SAPOperatingParameterResourceDetails();

        detailedResource.setId(operatingParameter.getId());
        detailedResource.setMonitorId(operatingParameter.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(operatingParameter.getSerialNumber() );

        if (operatingParameter.getDateOfInvoice() != null)
             detailedResource.setInoviceDate(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfInvoice()));
        else
            detailedResource.setInoviceDate(null);

        if(operatingParameter.getDateOfPaymentReceipt() != null) {
            detailedResource.setDatePayment(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfPaymentReceipt()));
        } else
            detailedResource.setDatePayment(null);


        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(operatingParameter.getMonth());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        //System.out.println(month == Calendar.FEBRUARY);


        detailedResource.setMonthYear( String.valueOf(month) +"." + operatingParameter.getYear().toString()) ;
        detailedResource.setExportUnit(operatingParameter.getExportNetGeneration().toString());

        detailedResource.setExportUnit("");

        detailedResource.setPlfPercent(String.format("%.2f",operatingParameter.getPlfCufActual()));
        detailedResource.setAppliTariff(String.format("%.2f",operatingParameter.getApplicableTariff()));
        detailedResource.setRevenue( String.format("%.2f", operatingParameter.getRevenue()));


        if(operatingParameter.getDateOfInvoice() != null) {
            detailedResource.setInoviceDate(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfInvoice()));
        } else
            detailedResource.setInoviceDate(null);

        if(operatingParameter.getDateOfPaymentReceipt() != null) {
            detailedResource.setDatePayment(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfPaymentReceipt()));
        } else
            detailedResource.setDatePayment(null);

        detailedResource.setCo2Emission(String.format("%.2f",operatingParameter.getCarbonDiOxideEmission()));
        detailedResource.setWaterSaved(String.format("%2f",operatingParameter.getWaterSaved()));
        detailedResource.setExportUnit(String.format("%2f",operatingParameter.getExportNetGeneration()));

//        if(operatingParameter.getRe() != null) {
//            detailedResource.setDatePayment(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfPaymentReceipt()));
//        } else
//            detailedResource.setDatePayment(null);


        detailedResource.setFileType(operatingParameter.getDocumentType());


//        if(operatingParameter.get() != null) {
//            detailedResource.setDatePayment(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfPaymentReceipt()));
//        } else
            detailedResource.setReceivedDate(null);
            detailedResource.setAvgRealisationPeriod(null);
            detailedResource.setFileType("");


//        detailedResource.setOverallrating(operatingParameter.getR);
//        detailedResource.setAvgRealisationPeriod(operatingParameter.getA());
//        detailedResource.setRevenue(operatingParameter.getRevenue());

        //detailedResource.setFileType(operatingParameter.getFileReference().);
        //detailedResource.setAvgRealisationPeriod(operatingParameter.;
        //detailedResource.setOverallrating(operatingParameter.getOverAllRating());
        

        return detailedResource;


    }

}
