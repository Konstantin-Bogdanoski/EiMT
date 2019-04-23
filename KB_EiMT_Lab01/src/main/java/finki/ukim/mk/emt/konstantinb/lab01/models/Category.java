package finki.ukim.mk.emt.konstantinb.lab01.models;

/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

public class Category implements Comparable{
    long ID;
    String name;

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

    @Override
    public boolean equals(Object obj) {
        Category temp = (Category) obj;
        return (this.getName().equals(temp.getName()));
    }
}
