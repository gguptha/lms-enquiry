package pfs.lms.enquiry.appraisal.loanappraisalkyc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.appraisal.LoanAppraisal;
import pfs.lms.enquiry.appraisal.LoanAppraisalRepository;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.repository.LoanApplicationRepository;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class LoanAppraisalKYCController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanAppraisalRepository loanAppraisalRepository;
    private final LoanAppraisalKYCRepository loanAppraisalKYCRepository;

    @PostMapping("/loanAppraisalKYCs/create")
    public ResponseEntity<LoanAppraisalKYC> createLoanAppraisalKYC(@RequestBody LoanAppraisalKYCResource loanAppraisalKYCResource,
                                                                   HttpServletRequest request) {

        LoanApplication loanApplication = loanApplicationRepository.getOne(loanAppraisalKYCResource.getLoanApplicationId());
        LoanAppraisal loanAppraisal = loanAppraisalRepository.findByLoanApplication(loanApplication)
                .orElseGet(() -> {
                    LoanAppraisal obj = new LoanAppraisal();
                    obj.setLoanApplication(loanApplication);
                    obj = loanAppraisalRepository.save(obj);
                    return obj;
                });
        LoanAppraisalKYC loanAppraisalKYC = new LoanAppraisalKYC();
        loanAppraisalKYC.setLoanAppraisal(loanAppraisal);
        loanAppraisalKYC.setSerialNumber(loanAppraisalKYCRepository.findByLoanAppraisalId(loanAppraisal.getId()).size() + 1);
        loanAppraisalKYC.setDateOfCompletion(loanAppraisalKYCResource.getDateOfCompletion());
        loanAppraisalKYC.setDocumentName(loanAppraisalKYCResource.getDocumentName());
        loanAppraisalKYC.setFileReference(loanAppraisalKYCResource.getFileReference());
        loanAppraisalKYC.setRemarks(loanAppraisalKYCResource.getRemarks());
        if (loanAppraisalKYCResource.getFileReference() != null)
            loanAppraisalKYC.setUploadDate(LocalDate.now());
        loanAppraisalKYC = loanAppraisalKYCRepository.save(loanAppraisalKYC);
        return ResponseEntity.ok(loanAppraisalKYC);
    }

    @PutMapping("/loanAppraisalKYCs/update")
    public ResponseEntity<LoanAppraisalKYC> updateLoanAppraisalKYC(@RequestBody LoanAppraisalKYCResource loanAppraisalKYCResource,
                                                         HttpServletRequest request) {

        LoanAppraisalKYC loanAppraisalKYC = loanAppraisalKYCRepository.findById(loanAppraisalKYCResource.getId())
                .orElseThrow(() -> new EntityNotFoundException(loanAppraisalKYCResource.getId().toString()));
        loanAppraisalKYC.setDateOfCompletion(loanAppraisalKYCResource.getDateOfCompletion());
        loanAppraisalKYC.setDocumentName(loanAppraisalKYCResource.getDocumentName());
        loanAppraisalKYC.setFileReference(loanAppraisalKYCResource.getFileReference());
        loanAppraisalKYC.setRemarks(loanAppraisalKYCResource.getRemarks());
        loanAppraisalKYC = loanAppraisalKYCRepository.save(loanAppraisalKYC);
        return ResponseEntity.ok(loanAppraisalKYC);
    }
}
