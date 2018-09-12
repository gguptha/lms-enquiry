package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.repository.PartnerRepository;

import javax.servlet.http.HttpServletRequest;

@ApiController
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerRepository partnerRepository;

    @GetMapping("/me")
    public ResponseEntity getLoggedinPartner(HttpServletRequest request){
        return ResponseEntity.ok(partnerRepository.findByUserName(request.getUserPrincipal().getName()));
    }
}
