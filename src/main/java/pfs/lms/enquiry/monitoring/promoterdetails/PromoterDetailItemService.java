package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PromoterDetailItemService implements IPromoterDetailItemService {

    private final PromoterDetailItemRepository promoterDetailItemRepository;

    @Override
    public PromoterDetailItem savePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId,
                                                     Integer itemsCount) {
        PromoterDetailItem promoterDetailItem = new PromoterDetailItem(
                resource.getSerialNumber(),
                resource.getShareHoldingCompany(),
                resource.getPaidupCapitalEquitySanction(),
                resource.getPaidupCapitalEquityCurrent(),
                resource.getEquityLinkInstrumentSanction(),
                resource.getEquityLinkInstrumentCurrent()
        );
        promoterDetailItem = promoterDetailItemRepository.save(promoterDetailItem);
        return promoterDetailItem;
    }

    @Override
    public PromoterDetailItem updatePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId) {

        PromoterDetailItem promoterDetailItem = promoterDetailItemRepository.findById(resource.getId())
                .orElseThrow(() -> new EntityNotFoundException(resource.getId().toString()));
        promoterDetailItem.setShareHoldingCompany(resource.getShareHoldingCompany());
        promoterDetailItem.setPaidupCapitalEquitySanction(resource.getPaidupCapitalEquitySanction());
        promoterDetailItem.setPaidupCapitalEquityCurrent(resource.getPaidupCapitalEquityCurrent());
        promoterDetailItem.setEquityLinkInstrumentSanction(resource.getEquityLinkInstrumentSanction());
        promoterDetailItem.setEquityLinkInstrumentCurrent(resource.getEquityLinkInstrumentCurrent());
        promoterDetailItem = promoterDetailItemRepository.save(promoterDetailItem);
        return promoterDetailItem;
    }
}
