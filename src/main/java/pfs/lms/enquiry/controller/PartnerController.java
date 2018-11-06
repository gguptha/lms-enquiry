package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@ApiController
@RequiredArgsConstructor
public class PartnerController {

    //private final PartnerRepository partnerRepository;

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity getLoggedinPartner(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal().getName());
        return ResponseEntity.ok(userRepository.findByEmail(request.getUserPrincipal().getName()));
    }
}
