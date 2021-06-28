package pfs.lms.enquiry.appraisal.syndicateconsortium;

import lombok.*;
import pfs.lms.enquiry.domain.AggregateRoot;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"bankName", "city"}, callSuper = false)
public class Bank extends AggregateRoot<Bank> implements Cloneable {

    private String bankName;
    private String city;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
