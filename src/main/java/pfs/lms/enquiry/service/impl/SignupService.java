package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.client.OAuthClient;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.exception.LmsException;
import pfs.lms.enquiry.repository.UserRepository;
import pfs.lms.enquiry.resource.SignupResource;
import pfs.lms.enquiry.service.ISignupService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupService implements ISignupService {

    //private final IPartnerService iPartnerService;
    private final UserRepository userRepository;

    private final OAuthClient oAuthClient;

    @Override
    public void signup(SignupResource signupResource) {

        //Partner partner = new Partner("TR0110", signupResource.getFirstName(), signupResource.getLastName(), signupResource.getEmail(), signupResource.getMobile(), signupResource.getPassword());
        //partner = iPartnerService.save(partner);

        User user = new User(signupResource.getFirstName(), signupResource.getLastName(), signupResource.getEmail(),
                "TR0110", true, signupResource.getEmail());
        user = userRepository.save(user);
        log.info("{} created", user);

        //Create User in OAuth
        ResponseEntity<Boolean> response = oAuthClient.signup(signupResource);

        if (!response.getBody())
            throw new LmsException("Error creating OAuth User", HttpStatus.PRECONDITION_FAILED);
    }

    @Override
    public ResponseEntity verify(String activation) {
        ResponseEntity response = oAuthClient.verify(activation);
        if (response.getStatusCode() != HttpStatus.OK)
            throw new LmsException("Email Verification Failed", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok().build();
    }


}
