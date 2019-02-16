package pfs.lms.enquiry.mail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;
import pfs.lms.enquiry.domain.AggregateRoot;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by sajeev on 16-Feb-19.
 */
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter (AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MailObject { //extends AggregateRoot<MailObject> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String toAddress;

    private String subject;

    private String mailContent;

    private String sendingApp;

    private String appObjectId;




}
