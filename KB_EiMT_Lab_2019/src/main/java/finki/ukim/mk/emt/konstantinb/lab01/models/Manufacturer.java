package finki.ukim.mk.emt.konstantinb.lab01.models;

import javax.persistence.*;

/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */
@Entity
@Table(name = "Manufacturer")
public class Manufacturer implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturerID")
    long iD;

    @Column(name = "manufacturerName")
    String name;

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

    public int compareTo(Object o) {
        Manufacturer temp = (Manufacturer) o;
        if(this.getName().equals(temp.getName()) && this.getID() == temp.getID())
            return 0;
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        Manufacturer temp = (Manufacturer) obj;
        return (this.getName().equals(temp.getName()));
    }
}