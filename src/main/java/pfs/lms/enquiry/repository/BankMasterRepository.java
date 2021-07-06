package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.AssistanceType;
import pfs.lms.enquiry.domain.BankMaster;

import java.util.UUID;

public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {

    public BankMaster findBankMasterByBankCountryKeyAndBankKey(String countryKey, String bankKey);
}
