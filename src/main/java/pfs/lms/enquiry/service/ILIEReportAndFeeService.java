package pfs.lms.enquiry.service;

import pfs.lms.enquiry.monitoring.lie.LIEReportAndFee;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFeeResource;

import java.util.List;

public interface ILIEReportAndFeeService {
    LIEReportAndFee save(LIEReportAndFeeResource resource, String username);
    LIEReportAndFee update(LIEReportAndFeeResource resource, String username);

    List<LIEReportAndFeeResource> getLIEReportAndFee(String loanApplicationId, String name);


}
