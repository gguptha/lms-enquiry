package pfs.lms.enquiry.monitoring.promoterdetails;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface IPromoterDetailItemService {
    PromoterDetailItem savePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId, Integer itemsCount, String userName);
    PromoterDetailItem updatePromoterDetailItem(PromoterDetailItem resource, UUID promoterDetailId, String userName) throws CloneNotSupportedException;
}
