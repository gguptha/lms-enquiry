package pfs.lms.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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

    private String createdByUserName;

    private LocalDate changedOn;

    private LocalTime changedAt;

    private String changedByUserName;

    @PrePersist
    public void init() {
        if(this.id == null || this.id.equals("")) {
            this.id = UUID.randomUUID().toString();
        }
    }
}