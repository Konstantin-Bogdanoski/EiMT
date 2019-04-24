package finki.ukim.mk.emt.konstantinb.lab01.models;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */
@Entity
@Table(name = "Product")
public class Product implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    long id;

    @Column(name = "productName")
    String name;

    @Column(name = "productDescription", length = 1000)
    String description;

    String linkToImg;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    Category category;

    @ManyToOne
    @JoinColumn(name = "manufacturerID")
    Manufacturer manufacturer;

    double price;

    @ManyToMany
    @JoinTable(name = "Product_Accessory",
    joinColumns = @JoinColumn(name = "productID"),
    inverseJoinColumns = @JoinColumn(name = "accessoryID"))
    List<Accessory> accessoryList;


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
    public List<Accessory> getAccessoryList() {
        return accessoryList;
    }
    public void setAccessoryList(List<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object obj) {
        Product temp = (Product) obj;
        return (this.getName().equals(temp.getName()) || this.getId()==temp.getId());
    }
}

