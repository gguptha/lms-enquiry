package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LIEResource;

import pfs.lms.enquiry.resource.LoanMonitorResource;
import pfs.lms.enquiry.service.ILIEService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor

public class LIEController {

    private final ILIEService lieService;


    @PostMapping("/loanApplications/lendersindependentengineers/create")
    public ResponseEntity create(@RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                lieService.save(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineer);
    }

    @PutMapping("/loanApplications/lendersindependentengineers/{id}")
    public ResponseEntity update(@PathVariable("id") String lieId, @RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                lieService.update(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lendersIndependentEngineer);

    }

    @GetMapping("/loanApplications/loanmonitor/{loanapplicationid}")
    public ResponseEntity getLendersIndependentEngineers(@PathVariable("loanapplicationid")
                                              String loanApplicationId,
                                               HttpServletRequest request)
    {
        List<LIEResource> lendersIndependentEngineers = lieService.getLendersIndependentEngineers(loanApplicationId,
                                        request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineers);
    }


}
