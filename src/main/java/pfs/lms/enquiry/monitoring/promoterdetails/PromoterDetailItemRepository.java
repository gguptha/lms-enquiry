package pfs.lms.enquiry.monitoring.promoterdetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PromoterDetailItemRepository extends JpaRepository<PromoterDetailItem, UUID> {
}
