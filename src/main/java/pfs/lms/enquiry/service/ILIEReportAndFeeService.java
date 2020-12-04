package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LIEReportAndFeeResource;
import pfs.lms.enquiry.resource.LIEResource;

import java.util.List;

public interface ILIEReportAndFeeService {
    LIEReportAndFee save(LIEReportAndFeeResource resource, String username);
    LIEReportAndFee update(LIEReportAndFeeResource resource, String username);

    List<LIEReportAndFeeResource> getLIEReportAndFee(String loanApplicationId, String name);


}
