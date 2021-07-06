package pfs.lms.enquiry.appraisal.furtherdetail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.appraisal.LoanAppraisal;
import pfs.lms.enquiry.appraisal.LoanAppraisalRepository;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.repository.LoanApplicationRepository;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class FurtherDetailController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanAppraisalRepository loanAppraisalRepository;
    private final FurtherDetailRepository furtherDetailRepository;

    @PutMapping("/furtherDetails/update")
    public ResponseEntity<FurtherDetail> updateFurtherDetails(@RequestBody FurtherDetailResource furtherDetailResource,
                                                                 HttpServletRequest request) {

        LoanApplication loanApplication = loanApplicationRepository.getOne(furtherDetailResource.getLoanApplicationId());
        LoanAppraisal loanAppraisal = loanAppraisalRepository.findByLoanApplication(loanApplication)
                .orElseGet(() -> {
                    LoanAppraisal obj = new LoanAppraisal();
                    obj.setLoanApplication(loanApplication);
                    obj = loanAppraisalRepository.save(obj);
                    return obj;
                });
        FurtherDetail furtherDetail = furtherDetailRepository.findByLoanAppraisalId(loanAppraisal.getId())
                .orElseGet(FurtherDetail::new);
        furtherDetail.setDate(furtherDetailResource.getDate());
        furtherDetail.setFurtherDetails(furtherDetailResource.getFurtherDetails());
        if (furtherDetail.getLoanAppraisal() == null)
            furtherDetail.setLoanAppraisal(loanAppraisal);
        furtherDetail = furtherDetailRepository.save(furtherDetail);
        return ResponseEntity.ok(furtherDetail);
    }
}
