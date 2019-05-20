package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping("/rest/products")
public class AllProductsRestfullController {
    private ProductService productService;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;

    public AllProductsRestfullController(ProductService pService, CategoryService cService, ManufacturerService mService){
        this.productService = pService;
        this.categoryService = cService;
        this.manufacturerService = mService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
