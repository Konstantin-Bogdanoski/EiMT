package finki.ukim.mk.emt.konstantinb.lab01.repositories;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.implementation.ManufacturerServiceImpl;
import finki.ukim.mk.emt.konstantinb.lab01.web.ProductController;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Repository
public class ProductRepository {
    private long counterID;
    private List<Product> productList;

    @PostConstruct
    public void init(){
        counterID = 1l;
        productList = new ArrayList<>();
    }

    public void addProduct(Product product){
        if(productList.stream().anyMatch(product1 -> {
            return product1.equals(product);
        }))
            throw new ProductAlreadyExistsException();
        product.setId(getNextId());
        productList.add(product);
    }

    public void removeProduct(Product product){
        if(productList.stream().noneMatch(product1 -> {
            return product1.equals(product);
        }))
            throw new ProductNotFoundException();
        productList.remove(product);
    }

    public List<Product> getProductList(){
        return productList;
    }

    public Optional<Product> findById(Long ID){
        return productList.stream()
                .filter(product -> {
                    return product.getId()==ID;
                }).findAny();
    }

    public Optional<Product> findByName(String name){
        return productList.stream()
                .filter(product -> {
                    return product.getName().equals(name);
                }).findAny();
    }

    private long getNextId() {return counterID++;}
}
