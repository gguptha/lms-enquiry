package pfs.lms.enquiry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LmsException.class)
    public ResponseEntity<LmsException> onQueueException(final LmsException e) {
        return ResponseEntity.status(e.getStatus()).body(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<LmsException> onIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new LmsException(e.getMessage(), HttpStatus.PRECONDITION_FAILED));
    }
}
