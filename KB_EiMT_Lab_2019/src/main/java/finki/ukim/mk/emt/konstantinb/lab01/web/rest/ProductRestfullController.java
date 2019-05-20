package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping(value = "/rest/products/{product_id}")
public class ProductRestfullController {

    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;

    public ProductRestfullController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public Product goToProduct(@PathVariable("product_id") String product_id) throws IOException {
        Long pid = Long.parseLong(product_id);
        Optional<Product> product = productService.getAllProducts().stream().filter(product1 -> {
            return product1.getId() == pid;
        }).findAny();
        if (!product.isPresent())
            throw new ProductNotFoundException();
        return product.get();
    }
}
