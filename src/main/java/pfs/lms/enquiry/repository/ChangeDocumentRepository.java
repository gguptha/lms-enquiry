package pfs.lms.enquiry.repository;

 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
 import pfs.lms.enquiry.domain.ChangeDocument;

 import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */

@Repository
@RepositoryRestResource
public interface ChangeDocumentRepository extends JpaRepository<ChangeDocument, Long> {



      Page<ChangeDocument> findByBusinessProcessNameAndLoanContractIdAndDateBetween(String businessProcessName, String loanContractId, Date dateFrom, Date dateTo, Pageable pageable);
      Page<ChangeDocument> findByBusinessProcessNameAndLoanContractIdAndDate(String businessProcessName, String loanContractId, Date date,   Pageable pageable);
      Page<ChangeDocument> findByBusinessProcessNameAndLoanContractId(String businessProcessName, String loanContractId, Pageable pageable);
      Page<ChangeDocument> findByBusinessProcessName(String businessProcessName, Pageable pageable);

      Page<ChangeDocument> findByBusinessProcessNameAndDateBetween(String businessProcessName, Date dateFrom, Date dateTo, Pageable pageable);
      Page<ChangeDocument> findByBusinessProcessNameAndDate(String businessProcessName, Date date,Pageable pageable);





}
