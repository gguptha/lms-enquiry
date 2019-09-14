package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.resource.PartnerResourceByEmail;
import pfs.lms.enquiry.resource.PartnerResourcesOrderByAlphabet;

import java.util.List;

public interface IUserService {

    List<User> searchUsers(String[] searchParameters);

}
