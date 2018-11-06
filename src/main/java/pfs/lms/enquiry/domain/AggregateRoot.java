package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AggregateRoot<A extends AbstractAggregateRoot<A>> extends AbstractAggregateRoot<A> {

    @Id
    protected UUID id;

    @Version
    @JsonIgnore
    protected Long version;

    protected LocalDate createdOn;

    protected LocalTime createdAt;

    protected String createdByUserName;

    protected LocalDate changedOn;

    protected LocalTime changedAt;

    protected String changedByUserName;

    @PrePersist
    public void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}
