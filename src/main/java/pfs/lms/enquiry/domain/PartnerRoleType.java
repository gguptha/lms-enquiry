package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PartnerRoleType extends AggregateRoot<PartnerRoleType> {

    private String roleCode;
    private String roleDescription;
}
