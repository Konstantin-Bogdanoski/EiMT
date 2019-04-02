package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.ProductRepository;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerService manufacturerService, CategoryService categoryService){
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    public Product addNewProduct(String name, long manufacturerID, long categoryID, String description, String linkToImg) throws ProductAlreadyExistsException, ManufacturerNotFoundException, CategoryNotFoundException{
        Optional<Manufacturer> manufacturer = manufacturerService
                .getAllManufacturers()
                .stream()
                .filter(v -> {
                    return v.getID() == manufacturerID;
                }).findAny();

        if(!manufacturer.isPresent()) throw new ManufacturerNotFoundException();

        Optional<Category> category = categoryService
                .getCategories()
                .stream()
                .filter(v -> {
                    return v.getID() == categoryID;
                }).findAny();
        if(!category.isPresent()) throw new CategoryNotFoundException();

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setManufacturer(manufacturer.get());
        newProduct.setCategory(category.get());
        newProduct.setDescription(description);
        newProduct.setLinkToImg(linkToImg);

        if(productRepository.getProductList().stream().anyMatch(v -> {
            return v.equals(newProduct);
        })) {
            return update(newProduct);
        }

        productRepository.addProduct(newProduct);
        return newProduct;
    }

    public Product addNewProduct(Product product, long manufacturerID, long categoryID) throws ProductAlreadyExistsException, ManufacturerNotFoundException, CategoryNotFoundException{
        Optional<Manufacturer> manufacturer = manufacturerService.getAllManufacturers().stream().filter(v -> { return v.getID() == manufacturerID; }).findAny();
        if(!manufacturer.isPresent()) throw new ManufacturerNotFoundException();

        Optional<Category> category = categoryService.getCategories().stream().filter(v -> { return v.getID() == categoryID; }).findAny();
        if(!category.isPresent()) throw new CategoryNotFoundException();

        product.setCategory(category.get());
        product.setManufacturer(manufacturer.get());

        if(productRepository.getProductList().stream().anyMatch(v -> {
            return v.equals(product);
        })) {
            return update(product);
        }
        productRepository.addProduct(product);
        return product;
    }

    public List<Product> getAllProducts(){
        return productRepository.getProductList();
    }

    public Product update(Product product) throws ProductNotFoundException{
        Optional<Product> productOptional = productRepository.getProductList().stream().filter(v -> {
            return v.equals(product);
        }).findAny();
        if(!productOptional.isPresent()) throw new ProductNotFoundException();

        Product temp = productOptional.get();
        if(temp.getManufacturer() == null)
            temp.setManufacturer(product.getManufacturer());

        if(temp.getCategory() == null)
            temp.setCategory(product.getCategory());

        return temp;
    }

    public void delete(Product product) throws ProductNotFoundException{
        productRepository.removeProduct(product);
    }

    public Product getById(Long productID) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(productID);
        if(!product.isPresent())
            throw new ProductNotFoundException();
        return product.get();
    }

    public Product getByName(String name) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findByName(name);
        if(!product.isPresent())
            throw new ProductNotFoundException();
        return product.get();
    }
}
