package pfs.lms.enquiry.service.changedocs;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfs.lms.enquiry.domain.ChangeDocument;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IChangeDocumentService {

    public ChangeDocument createChangeDocument(UUID objectId,
                                               String loanContractId,
                                               Object oldObject,
                                               Object changedObject,
                                               String action,
                                               String userName,
                                               String businessProcessName,
                                               String subProcessName, String key1, String key2);


    public ChangeDocument saveChangeDocument(ChangeDocument changeDocument);

    public ChangeDocument updateChangeDocument(ChangeDocument changeDocument, Object object);



    Page<ChangeDocument> findByLoanNumber(String loanNumber, Pageable pageable);

    Page<ChangeDocument> findByLoanNumberAndDateBetween(String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);


    Page<ChangeDocument> findByLoanBusinessProcessId(Long id, Pageable pageable);
    Page<ChangeDocument> findByLoanBusinessProcessIdAndLoanNumberAndDateBetween(Long id, String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);
    Page<ChangeDocument> findByLoanBusinessProcessIdAndDateBetween(Long id, Date dateFrom, Date dateTo, Pageable pageable);
    List<ChangeDocument> findByLoanBusinessProcessIdAndDateBetween(Long id, Date dateFrom, Date dateTo);
    Page<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date, Pageable pageable);
    Page<ChangeDocument> findByLoanBusinessProcessIdAndDate(Long id, Date date, Pageable pageable);
    Page<ChangeDocument> findByLoanBusinessProcessIdAndLoanNumberAndDate(Long id, String loaNumber, Date date, Pageable pageable);

}
