package finki.ukim.mk.emt.konstantinb.lab01.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Entity
@Table(name = "UserRole")
public class UserRole implements GrantedAuthority {
    @Id
    @Column(name = "iD")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    @Column(name = "Name")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    public long getID() {
        return iD;
    }

    public void setID(long ID) {
        this.iD = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
