package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface ProductService {
    Product addNewProduct(String name, long manufacturerID, long categoryID, String description, String linkToImg) throws ProductAlreadyExistsException, ManufacturerNotFoundException, CategoryNotFoundException;
    Product addNewProduct(Product product, long manufacturerID, long categoryID) throws ProductAlreadyExistsException;
    List<Product> getAllProducts();
    Product update(Product product) throws ProductNotFoundException;
    void delete(Product product) throws ProductNotFoundException;
    Product getById(Long productID) throws ProductNotFoundException;
    Product getByName(String name) throws ProductNotFoundException;
}
