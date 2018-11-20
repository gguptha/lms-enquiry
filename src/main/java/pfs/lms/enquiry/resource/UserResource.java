package pfs.lms.enquiry.resource;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import pfs.lms.enquiry.exception.LmsException;

@Data
public class SignupResource {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;

    public SignupResource(String firstName, String lastName, String email, String mobile, String password) {

        if(!isValidEmail(email))
            throw new LmsException(String.format("Invalid email address: %s", email), HttpStatus.PRECONDITION_FAILED);

        if(!isValidPassword(password))
            throw new LmsException(String.format("Invalid password: %s", password), HttpStatus.PRECONDITION_FAILED);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    private static final String emailRegex = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")";

    private static final String passwordRegex = "^(?=.*[A-Z]).*(?=.*[0-9]).*(?=.*[a-z]).{8}$";

    private static boolean isValidEmail(String address) {
        return address == null ? false : address.matches(emailRegex);
    }

    private static boolean isValidPassword(String password) {
        return password == null ? false : password.matches(passwordRegex);
    }

}
