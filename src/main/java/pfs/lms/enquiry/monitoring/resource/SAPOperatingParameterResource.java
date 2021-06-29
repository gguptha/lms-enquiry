package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.monitoring.operatingParameters.OperatingParameter;
import pfs.lms.enquiry.monitoring.operatingParameters.OperatingParameterPLFResource;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
public class SAPOperatingParameterResource {

    @JsonProperty(value = "d")
    private SAPOperatingParameterResourceDetails sapOperatingParameterResourceDetails;

    public SAPOperatingParameterResource(SAPOperatingParameterResourceDetails sapOperatingParameterResourceDetails) {
        this.sapOperatingParameterResourceDetails = sapOperatingParameterResourceDetails;
    }



    public SAPOperatingParameterResourceDetails mapToSAP(OperatingParameter operatingParameter) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPOperatingParameterResourceDetails detailedResource = new SAPOperatingParameterResourceDetails();

        detailedResource.setId(operatingParameter.getId());
        detailedResource.setMonitorId(operatingParameter.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(operatingParameter.getSerialNumber().toString());

        if (operatingParameter.getDateOfInvoice() != null)
             detailedResource.setInoviceDate(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfInvoice()));
        else
            detailedResource.setInoviceDate(null);

        if(operatingParameter.getDateOfPaymentReceipt() != null) {
            detailedResource.setDatePayment(dataConversionUtility.convertDateToSAPFormat(operatingParameter.getDateOfPaymentReceipt()));
        } else
            detailedResource.setDatePayment(null);

//        if(operatingParameter.getDateOfPaymentReceipt() != null) {
//            detailedResource.setNextduedateofexternalrating(dataConversionUtility.convertDateToSAPFormat(borrowerFinancials.getNextDueDateOfExternalRating()));
//        } else
//            detailedResource.setNextduedateofexternalrating(null);


        detailedResource.setMonthYear(operatingParameter.getMonth().toString()  );
        detailedResource.setExportUnit(operatingParameter.getExportNetGeneration());
        detailedResource.setPlfPercent(operatingParameter.getPlfCufActual());
        detailedResource.setAppliTariff(operatingParameter.getApplicableTariff());
        detailedResource.setRevenue(operatingParameter.getRevenue());
        detailedResource.setCo2Emission(operatingParameter.getCarbonDiOxideEmission().toString());
        detailedResource.setWaterSaved(operatingParameter.getWaterSaved().toString());

        //detailedResource.setFileType(operatingParameter.getFileReference().);
        //detailedResource.setAvgRealisationPeriod(operatingParameter.;
        //detailedResource.setOverallrating(operatingParameter.getOverAllRating());
        

        return detailedResource;


    }

}
