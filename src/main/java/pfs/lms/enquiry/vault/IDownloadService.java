package pfs.lms.enquiry.vault;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface IDownloadService {
    ResponseEntity<Resource> getFile(
            UUID uuid,
            Optional<String> requestEtagOpt,
            Optional<Date> ifModifiedSinceOpt
    );
}
