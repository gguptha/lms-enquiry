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

    Page<ChangeDocument> findByLoanNumber(String loanNumber, Pageable pageable);

    Page<ChangeDocument> findByLoanNumberAndDateBetween(String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);


    Page<ChangeDocument> findByLoanApplication(Long id, Pageable pageable);

    Page<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date, Pageable pageable);



}
