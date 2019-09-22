package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by sajeev on 22-Sep-19.
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class State extends AggregateRoot<State> {

    private String code;
    private String name;
}
