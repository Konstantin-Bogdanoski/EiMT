package finki.ukim.mk.emt.konstantinb.lab01.models;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */
@Entity
@Table(name = "Category")
public class Category implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    long ID;

    @Column(name = "categoryName")
    String name;

    @OneToMany(mappedBy = "manufacturer",
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Product> productList;

    public List<Product> getProducts() {
        return productList;
    }
    public void setProducts(List<Product> productList) {
        this.productList = productList;
    }
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
