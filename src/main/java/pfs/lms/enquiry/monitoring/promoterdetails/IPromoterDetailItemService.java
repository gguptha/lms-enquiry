package pfs.lms.enquiry.monitoring.promoterdetails;

import java.util.UUID;

public interface IPromoterDetailItemService {
    PromoterDetailItem savePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId, Integer itemsCount);
    PromoterDetailItem updatePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId);
}
