package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User extends AggregateRoot<User> {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean status;
    private String userName;
    private String sapBPNumber;

    public User(String firstName, String lastName, String email, String role, boolean status, String userName, String sapBPNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.userName = userName;
        this.sapBPNumber = sapBPNumber;
        registerEvent(UserCreated.of(this));
    }

    @Value
    @RequiredArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static class UserCreated {
        final User user;
    }
}
