package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import pfs.lms.enquiry.domain.FinancialCovenants;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.utils.DataConversionUtility;

import java.text.ParseException;

/**
 * Created by sajeev on 28-Jun-21.
 */
public class SAPFinancialCovenantsResource {

    @JsonProperty(value = "d")
    private SAPFinancialCovenantsResourceDetails sapFinancialCovenantsResourceDetails;

    public SAPFinancialCovenantsResource(SAPFinancialCovenantsResourceDetails sapFinancialCovenantsResourceDetails) {
        this.sapFinancialCovenantsResourceDetails = sapFinancialCovenantsResourceDetails;
    }



    public SAPFinancialCovenantsResourceDetails mapToSAP(FinancialCovenants financialCovenants) throws ParseException {

        DataConversionUtility dataConversionUtility = new DataConversionUtility();

        SAPFinancialCovenantsResourceDetails detailedResource = new SAPFinancialCovenantsResourceDetails();

        detailedResource.setFinCovId(financialCovenants.getId());
        detailedResource.setMonitorId(financialCovenants.getLoanMonitor().getId().toString());
        detailedResource.setSerialNo(financialCovenants.getSerialNumber().toString());

        detailedResource.setFinancialcovenanttype(financialCovenants.getFinancialCovenantType());
        detailedResource.setFinancialyear( Double.toString(financialCovenants.getFinancialYear()));
        detailedResource.setDebtequityratio(financialCovenants.getDebtEquityRatio());
        detailedResource.setDscr(financialCovenants.getDscr());
        detailedResource.setToltnw(financialCovenants.getTolTnw());
        detailedResource.setRemarksfordeviation(financialCovenants.getRemarksForDeviation());

        return detailedResource;


    }

}
