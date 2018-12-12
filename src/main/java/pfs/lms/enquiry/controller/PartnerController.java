package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@ApiController
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerRepository partnerRepository;

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity getLoggedinPartner(HttpServletRequest request) {
        if(request.getUserPrincipal().getName().equals("admin"))
            return ResponseEntity.ok(userRepository.findByEmail("admin@gmail.com"));
        else
            return ResponseEntity.ok(userRepository.findByEmail(request.getUserPrincipal().getName()));
    }

    @GetMapping("/partners/byPrincipal")
    public ResponseEntity getPartnerByEmail(HttpServletRequest request) {
        if(request.getUserPrincipal().getName().equals("admin"))
            return ResponseEntity.ok(partnerRepository.findByEmail("admin@gmail.com"));
        else
            return ResponseEntity.ok(partnerRepository.findByEmail(request.getUserPrincipal().getName()));
    }
}
