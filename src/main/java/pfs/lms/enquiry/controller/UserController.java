package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.resource.UserResource;
import pfs.lms.enquiry.service.ISignupService;

@ApiController
@RequiredArgsConstructor
public class UserController
{
    private final ISignupService iSignupService;

    @PostMapping("/user")
    public ResponseEntity signup(@RequestBody UserResource userResource)
    {
        // Create the user.
        iSignupService.signup(userResource);

        return ResponseEntity.ok().build();
    }
}
