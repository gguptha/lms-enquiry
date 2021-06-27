package pfs.lms.enquiry.monitoring.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import pfs.lms.enquiry.monitoring.resource.SAPLIEReportAndFeeResource;
import pfs.lms.enquiry.monitoring.resource.SAPLIEResource;

public interface ISAPLoanMonitoringIntegrationService {

    String fetchCSRFToken();

    Object postResource(SAPLIEResource saplieResource);

    Object postResourceToSAP(Object resource, String serviceUri, HttpMethod httpMethod, MediaType mediaType);

 }
