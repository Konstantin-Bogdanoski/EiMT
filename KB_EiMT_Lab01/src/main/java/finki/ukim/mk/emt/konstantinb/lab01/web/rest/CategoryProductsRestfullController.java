package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping(value = "/rest/product/category/{categoryId}")
public class CategoryProductsRestfullController {
    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;

    public CategoryProductsRestfullController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Product> getProducts(@PathVariable("categoryId") String categoryId) throws IOException {
        Long cid = Long.parseLong(categoryId);
        Optional<Category> category = categoryService.getCategories().stream().filter(cat -> {
            return cat.getID() == cid;
        }).findAny();
        if (!category.isPresent())
            throw new CategoryNotFoundException();

        List<Product> products = new ArrayList<>();
        for (Product product: productService.getAllProducts()) {
            if(product.getCategory().equals(category.get()))
                products.add(product);
        }
        return products;
    }
}
