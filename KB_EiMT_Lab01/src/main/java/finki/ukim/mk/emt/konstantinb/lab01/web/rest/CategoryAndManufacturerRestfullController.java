package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping(value = "/rest/product/category/{categoryId}/manufacturer/{manufacturerId}")
public class CategoryAndManufacturerRestfullController {
    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;

    public CategoryAndManufacturerRestfullController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    @ResponseBody
    public List<Product> getProducts(@PathVariable("categoryId") String category_ID, @PathVariable("manufacturerId") String manufacturer_ID){
        List<Product> products = new ArrayList<>();

        Optional<Category> category = categoryService.getCategories().stream().filter(cat -> {
            return cat.getID() == Long.parseLong(category_ID);
        }).findAny();

        if(!category.isPresent())
            throw new CategoryNotFoundException();

        Optional<Manufacturer> manufacturer = manufacturerService.getAllManufacturers().stream().filter(man -> {
            return man.getID() == Long.parseLong(manufacturer_ID);
        }).findAny();

        if(!manufacturer.isPresent())
            throw new ManufacturerNotFoundException();

        for (Product product: productService.getAllProducts()) {
            if(product.getCategory().equals(category.get()) && product.getManufacturer().equals(manufacturer.get()))
                products.add(product);
        }
        return products;
    }
}
