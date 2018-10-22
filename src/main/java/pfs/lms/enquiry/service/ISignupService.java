package pfs.lms.enquiry.service;

import org.springframework.http.ResponseEntity;
import pfs.lms.enquiry.resource.SignupResource;

public interface ISignupService {
    void signup(SignupResource signupResource);
    ResponseEntity verify(String activation);
}
