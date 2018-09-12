package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntity {

    @Id
    protected String id;

    @Version
    @JsonIgnore
    private Long version;

    private LocalDate createdOn;

    private LocalTime createdAt;

    @Size(max = 20)
    private String createdByUserName;

    private LocalDate changedOn;

    private LocalTime changedAt;

    @Size(max = 20)
    private String changedByUserName;

    @PrePersist
    public void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}