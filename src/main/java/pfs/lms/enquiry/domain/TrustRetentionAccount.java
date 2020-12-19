package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TrustRetentionAccount extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private String bankKey;

    private String tRABankName;

    private String branch;

    private String address;

    private String beneficiaryName;

    private String iFSCCode;

    private String accountNumber;

    private String contactName;

    private String accountType;

    private String contactNumber;

    private String email;

    private String pFSAuthorisedPersonBPCode;

    private String pFSAuthorisedPerson;
}
