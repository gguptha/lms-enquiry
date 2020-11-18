package pfs.lms.enquiry.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.domain.LoanContractExtension;
import pfs.lms.enquiry.resource.LoanContractExtensionResource;
import pfs.lms.enquiry.service.ILoanContractExtensionService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor

public class LoanContractExtensionController {

    private final ILoanContractExtensionService iLoanContractExtensionService;

    @PostMapping("/loancontractextension/{loanapplicationid}")
    public ResponseEntity create(@PathVariable("loanapplicationid") String loanapplicationid, @RequestBody LoanContractExtensionResource resource, HttpServletRequest request) {
        resource.setLoanApplicationId(UUID.fromString(loanapplicationid));
        LoanContractExtension loanContractExtension =
                iLoanContractExtensionService.save(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(loanContractExtension);

    }

    @PutMapping("/loancontractextension/{id}")
    public ResponseEntity update(@PathVariable("id") String loanContractExtensionId, @RequestBody LoanContractExtensionResource resource, HttpServletRequest request) {
        LoanContractExtension loanContractExtension =
                iLoanContractExtensionService.update(resource, request.getUserPrincipal().getName());


        return ResponseEntity.ok(loanContractExtension);

    }

}
