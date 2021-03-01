package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.resource.PartnerResourceByEmail;
import pfs.lms.enquiry.resource.PartnerResourcesOrderByAlphabet;

import java.util.List;

public interface IPartnerService {
    Partner getOne(String username);
    Partner save(Partner partner);
    Partner migrate(Partner partner);
    Partner update(Partner partner);
    List<Partner> searchPartners(String[] searchParameters);
    PartnerResourcesOrderByAlphabet getPartnersOrderedByAlphabets();

    List<PartnerResourceByEmail> getPartnerByEmail();
    Partner   getPartnerByEmail(String email);

    List<Partner> getLendersIndependentEngineers();
    List<Partner> getLendersFinancialAdvisors();
}
