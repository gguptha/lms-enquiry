package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AssistanceType extends AggregateRoot<AssistanceType> {

    private String code;

    private String value;
}
