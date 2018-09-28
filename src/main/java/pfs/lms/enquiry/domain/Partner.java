package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Partner extends AggregateRoot<Partner>{

    private Integer partyNumber;

    /**
     * 001 - Person
     * 002 - Organization
     */
    private Integer partyCategory;

    /**
     * TR0110 - Prospect
     * ZLM013 - Appraisal Officer
     * ZLM010 - Co-Appraisal Officer
     * ZLM023 - PFS IT Team
     * ZLM001 - Promoter
     * TR0100 - Main Loan Partner
     * TR010 - Co-Borrower
     */
    private String partyRole;

    @Size(max = 100)
    private String partyName1;

    @Size(max = 100)
    private String partyName2;

    @Size(max = 100)
    private String contactPersonName;

    private String addressLine1;

    private String addressLine2;

    private String street;

    private String city;

    private String state;

    @Size(max = 8)
    private String postalCode;

    @Size(max = 2)
    private String country = "IN";

    @Column(unique = true)
    private String email;

    @Size(max = 15)
    private String contactNumber;

    @Size(max = 100)
    private String groupCompany;

    @Size(max = 20)
    @Column(unique = true)
    private String userName;

    @Size(max = 100)
    private String password;

    private String pan;

    public Partner(Integer partyNumber, Integer partyCategory, String partyRole, @Size(max = 100) String partyName1, @Size(max = 100) String partyName2, @Size(max = 100) String contactPersonName, String addressLine1, String addressLine2, String street, String city, String state, @Size(max = 8) String postalCode, @Size(max = 2) String country, String email, @Size(max = 15) String contactNumber, @Size(max = 100) String groupCompany, @Size(max = 20) String userName, @Size(max = 100) String password, String pan) {
        this.partyNumber = partyNumber;
        this.partyCategory = partyCategory;
        this.partyRole = partyRole;
        this.partyName1 = partyName1;
        this.partyName2 = partyName2;
        this.contactPersonName = contactPersonName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.contactNumber = contactNumber;
        this.groupCompany = groupCompany;
        this.userName = userName;
        this.password = password;
        this.pan = pan;
        registerEvent(PartnerCreated.of(this));
    }

    public Partner(@Size(max = 20) String userName, @Size(max = 100) String password) {
        this.userName = userName;
        this.password = password;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class PartnerCreated {
        final Partner partner;
    }

}
