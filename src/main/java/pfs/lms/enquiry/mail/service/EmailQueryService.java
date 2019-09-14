package pfs.lms.enquiry.mail.service;

import org.springframework.mail.SimpleMailMessage;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.resource.MailSearchResource;

import java.util.List;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface EmailQueryService {


    public List<MailObject> searchMailObject(String[] queryParams);

    public List<MailObject> searchMailObjectByResource(MailSearchResource searchResource);


}
