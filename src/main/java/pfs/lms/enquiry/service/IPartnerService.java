package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.Partner;

public interface IPartnerService {
    Partner getOne(String username);
    Partner save(Partner partner);
    Partner migrate(Partner partner);
    Partner update(Partner partner);
}
