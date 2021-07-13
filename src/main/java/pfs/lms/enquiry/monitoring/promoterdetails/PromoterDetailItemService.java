package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.service.changedocs.IChangeDocumentService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PromoterDetailItemService implements IPromoterDetailItemService {

    private final PromoterDetailItemRepository promoterDetailItemRepository;
    private final IChangeDocumentService changeDocumentService;

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

//        PromoterDetail promoterDetail = promoterDetailItem.getP
//
//        // Change Documents for Promoter Details
//        changeDocumentService.createChangeDocument(
//                promoterDetailItem.getLoanMonitor().getId(), promoterDetail.getId().toString(),null,
//                promoterDetail.getLoanMonitor().getLoanApplication().getLoanContractId(),
//                null,
//                promoterDetail,
//                "Created",
//                username,
//                "Monitoring", "Promoter Details");

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
