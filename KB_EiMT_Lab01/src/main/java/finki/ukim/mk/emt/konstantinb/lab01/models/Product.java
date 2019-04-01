package finki.ukim.mk.emt.konstantinb.lab01.models;
/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

public class Product implements Comparable{
    long id;
    String name;
    String description;
    String linkToImg;
    Category category;
    Manufacturer manufacturer;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLinkToImg() {
        return linkToImg;
    }
    public void setLinkToImg(String linkToImg) {
        this.linkToImg = linkToImg;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int compareTo(Object o) {
        Product temp = (Product) o;
        if(this.getId() == temp.getId() &&
                this.getCategory() == temp.getCategory() &&
                this.getDescription().equals(temp.getDescription()) &&
                this.getCategory() == temp.getCategory() &&
                this.getManufacturer() == temp.getManufacturer())
            return 0;
        return -1;
    }
}

