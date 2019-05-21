package finki.ukim.mk.emt.konstantinb.lab01.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Entity
@Table(name = "UserRole")
public class UserRole {
    public enum Role {
        ADMIN,
        USER
    }

    @Id
    @Column(name = "Role")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
