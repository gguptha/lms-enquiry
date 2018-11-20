package pfs.lms.enquiry.service;

import org.springframework.http.ResponseEntity;
import pfs.lms.enquiry.resource.SignupResource;
import pfs.lms.enquiry.resource.UserResource;

public interface ISignupService {

    void signup(SignupResource signupResource);

    /*
     *  Creates a new user.
     */
    void signup(UserResource userResource);

    ResponseEntity verify(String activation);
}
