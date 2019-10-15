package pfs.lms.enquiry.service;

import org.springframework.http.ResponseEntity;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.resource.SignupResource;
import pfs.lms.enquiry.resource.UserResource;

import java.util.concurrent.CompletableFuture;

public interface ISignupService {

    void signup(SignupResource signupResource);

    /*
     *  Creates a new user.
     */
    void signup(UserResource userResource);

    ResponseEntity verify(String activation);

     public String sendSignUpNotificationMail(String emailId, String fName, String lName) ;

}
