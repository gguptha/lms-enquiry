package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.service.IPartnerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerService implements IPartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getOne(String username) {
        return partnerRepository.findByUserName(username);
    }

    @Override
    public Partner save(Partner partner) {

        //Check if rhe partner already exist
        Partner existing = partnerRepository.findByEmail(partner.getEmail());

        //If exists return the existing partner
        if (existing != null) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("---------------------- Partner Already exists : EMAIL : " + partner.getEmail());
            System.out.println("--------------------------------------------------------------------------------");
            return existing;
        }
        //If not create a new partner and return
        else {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("---------------------- Created New Partner : EMAIL : " + partner.getEmail());
            System.out.println("--------------------------------------------------------------------------------");
            partner.setId(null);
            return partnerRepository.saveAndFlush(partner);
        }
    }

    @Override
    public Partner update(Partner partner) {
        return null;
    }
}
