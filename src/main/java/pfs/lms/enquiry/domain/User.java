package pfs.lms.enquiry.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User extends AggregateRoot<User> {

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String role;
    private boolean status;
    private String userName;
    private String sapBPNumber;
    private String riskDepartment;
    @Nullable
    private boolean departmentHead;

    public User(String firstName, String lastName, String email, String role, boolean status, String userName, String sapBPNumber, String riskDepartment, boolean departmentHead) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.userName = userName;
        this.sapBPNumber = sapBPNumber;
        this.riskDepartment = riskDepartment;
        this.departmentHead = departmentHead;
        registerEvent(UserCreated.of(this));
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole() {
        return this.role;
    }

    public boolean isStatus() {
        return this.status;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSapBPNumber() {
        return this.sapBPNumber;
    }

    public String getRiskDepartment() {
        return this.riskDepartment;
    }

    public boolean isDepartmentHead() {
        return this.departmentHead;
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class UserCreated {
        final User user;
    }
}
