package finki.ukim.mk.emt.konstantinb.lab01.models;

import java.util.Objects;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public class Accessory {
    Long ID;
    String name;

    public Accessory(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accessory accessory = (Accessory) o;
        return getID().equals(accessory.getID()) &&
                getName().equals(accessory.getName());
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName());
    }
}
