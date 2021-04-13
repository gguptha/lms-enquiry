package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.javers.core.metamodel.annotation.DiffIgnore;
import sun.security.x509.SerialNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sajeev on 08-Apr-21.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PartnerContact extends AggregateRoot<PartnerContact> {

//    @DiffIgnore
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name = "partner__id", nullable = false)
//    private Partner partner;

    private Integer serialNumber;

    private String printInDemandLetter;

    private String loanContractId;

    private String contactName;

    private String branchAddress;

    private String designation;

    private String department;

    private String mobilePhoneNumber;

    private String landPhoneNumber;

    private String email;

    private String faxNumber;





}
