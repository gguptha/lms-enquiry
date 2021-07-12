package pfs.lms.enquiry.monitoring.promoterdetails;

import java.util.List;

public interface IPromoterDetailService {
    PromoterDetail savePromoterDetails(PromoterDetailResource resource, String username);
    PromoterDetail updatePromoterDetails(PromoterDetailResource resource, String username) throws CloneNotSupportedException;
    List<PromoterDetailResource> getPromoterDetails(String loanApplicationId, String name);
}
