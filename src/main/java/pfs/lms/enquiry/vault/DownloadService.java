package pfs.lms.enquiry.vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class DownloadService implements IDownloadService {

    private final FileStorage storage;

    @Autowired
    public DownloadService(FileStorage storage) {
        this.storage = storage;
    }

    public ResponseEntity<Resource> getFile(
            UUID uuid,
            Optional<String> requestEtagOpt,
            Optional<Date> ifModifiedSinceOpt
    ) {
        // String contextPath = extractContextPath(request);
        String contextPath = "/enquiry";
        HttpMethod method = HttpMethod.GET;
        return findExistingFile(method, uuid, contextPath)
                .map(file -> file.handle(requestEtagOpt, ifModifiedSinceOpt))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    private String extractContextPath(HttpServletRequest request) {
        String protocol =  (request.getHeader("x-forwarded-proto") == null || request.getHeader("x-forwarded-proto").isEmpty())? "":request.getHeader("x-forwarded-proto");
        String host =  (request.getHeader("x-forwarded-host") == null || request.getHeader("x-forwarded-host").isEmpty())? "":request.getHeader("x-forwarded-host");
        String prefix = (request.getHeader("x-forwarded-prefix") == null || request.getHeader("x-forwarded-prefix").isEmpty())? "":request.getHeader("x-forwarded-prefix");

        String contextPath = null;
        if(!(protocol.isEmpty() && host.isEmpty() && prefix.isEmpty()))
            //Append forwarded prefix in case of proxied request
            contextPath = prefix + request.getContextPath();
        else
            contextPath = request.getContextPath();
        return contextPath;
    }

    private Optional<ExistingFile> findExistingFile(HttpMethod method, @PathVariable UUID uuid, String contextPath) {
        return storage
                .findFile(uuid)
                .map(pointer -> new ExistingFile(contextPath, method, pointer, uuid));
    }
}
