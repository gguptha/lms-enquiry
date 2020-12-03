package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LIEResource;

import pfs.lms.enquiry.service.ILIEService;

import javax.servlet.http.HttpServletRequest;
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

    @PutMapping("/lendersindependentengineers/{id}")
    public ResponseEntity update(@PathVariable("id") String lieId, @RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                lieService.update(resource, request.getUserPrincipal().getName());


        return ResponseEntity.ok(lendersIndependentEngineer);

    }
}
