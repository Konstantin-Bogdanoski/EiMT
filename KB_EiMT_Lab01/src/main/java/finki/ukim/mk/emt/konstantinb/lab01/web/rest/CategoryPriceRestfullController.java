package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping(value = "/rest/product/category/{categoryId}/price")
public class CategoryPriceRestfullController {
    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;

    public CategoryPriceRestfullController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public Map<String, Double> getPrice(@PathVariable ("categoryId") String categoryId) throws IOException {
        Category category = categoryService.getById(Long.parseLong(categoryId));

        return Collections.singletonMap("Total price", productService.getPrice(category));
    }
}
