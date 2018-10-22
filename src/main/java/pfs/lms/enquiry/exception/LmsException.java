package pfs.lms.enquiry.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class LmsException extends RuntimeException{

    private String message;
    private HttpStatus status;

    public LmsException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace(){
        return super.getStackTrace();
    }


}
