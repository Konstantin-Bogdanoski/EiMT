package finki.ukim.mk.emt.konstantinb.lab01.models;

public class Category implements Comparable{
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
        Category temp = (Category) o;
        if(this.getID() == temp.getID() &&
                this.getName().equals(temp.getName()))
            return 0;
        return -1;
    }
}
