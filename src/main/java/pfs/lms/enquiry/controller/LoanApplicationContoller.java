package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.service.ILoanApplicationService;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoanApplicationContoller {

    private final ILoanApplicationService loanApplicationService;

    @PostMapping("/loanapplications")
    public ResponseEntity add(@RequestBody LoanApplicationResource resource, HttpServletRequest request){
        return ResponseEntity.ok(loanApplicationService.save(resource,request.getUserPrincipal().getName()));
    }
}
