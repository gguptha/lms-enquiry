package pfs.lms.enquiry.monitoring.service;

import pfs.lms.enquiry.monitoring.resource.SAPLIEReportAndFeeResource;
import pfs.lms.enquiry.monitoring.resource.SAPLIEResource;

public interface ISAPLoanMonitoringIntegrationService {

    String fetchCSRFToken();

    Object postResource(SAPLIEResource saplieResource);

    Object postResourceToSAP(Object resource, String serviceUri);

 }
