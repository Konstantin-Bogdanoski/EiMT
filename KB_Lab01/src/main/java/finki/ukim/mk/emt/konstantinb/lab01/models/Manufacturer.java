package finki.ukim.mk.emt.konstantinb.lab01.models;

public class Manufacturer implements Comparable {
    private long ID;
    private String name;

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
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
}