package pfs.lms.enquiry.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sajeev on 27-Jun-21.
 */
@Getter
@Setter
public class FileResource {

    String mimeType;
    byte[] documentContent;
}
