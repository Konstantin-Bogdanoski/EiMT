package finki.ukim.mk.emt.konstantinb.lab01.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Entity
@Table(name = "Transaction")
public class Transaction implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionID")
    long id;

    @Column(name = "transactionDate")
    Date transactionDate;

    @Column(name = "transactionUser")
    String username;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productID")
    Product purchasedProduct;

    @Column(name = "transactionAmount")
    double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Product getPurchasedProduct() {
        return purchasedProduct;
    }

    public void setPurchasedProduct(Product purchasedProduct) {
        this.purchasedProduct = purchasedProduct;
    }


    @Override
    public int compareTo(Object o) { //compares date only
        Transaction t = (Transaction) o;
        if (t.getTransactionDate().before(this.getTransactionDate()))
            return 1;
        if (t.getTransactionDate().after(this.getTransactionDate()))
            return -1;
        return 0;
    }
}
