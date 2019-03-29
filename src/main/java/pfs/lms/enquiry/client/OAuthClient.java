package pfs.lms.enquiry.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.resource.SignupResource;

@FeignClient(name = "oAuthClient", url = "${oauth.baseUrl}")
public interface OAuthClient {

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    ResponseEntity<Boolean> signup(@RequestBody SignupResource signupResource);

    @RequestMapping(value = "/api/admin/signup", method = RequestMethod.POST)
    ResponseEntity<Boolean> adminSignUp(@RequestBody SignupResource signupResource);

    @RequestMapping(value = "/api/signup/verify/{activation}", method = RequestMethod.PUT)
    ResponseEntity verify(@PathVariable("activation") String activation);

    @RequestMapping(value = "/api/password/modify", method = RequestMethod.PUT)
    ResponseEntity modifyPassword(@RequestHeader("Authorization")String token, @RequestBody SignupResource signupResource);

    @RequestMapping(value = "/api/password/reset", method = RequestMethod.PUT)
    ResponseEntity resetPassword(@RequestBody SignupResource signupResource);
}
