package finki.ukim.mk.emt.konstantinb.lab01.web;
/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ProductNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ManufacturerService;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;
    private Product product;
    private Long manufacturerID;
    private Long categoryID;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService){
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
        product = new Product();
        manufacturerID = 1l;
        categoryID = 1l;
    }

    @GetMapping("productPage")
    public String products(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        model.addAttribute("categories", categoryService.getCategories());
        return "productPage";
    }

    @GetMapping("productAdd")
    public String addProduct(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);
        return "productAdd";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @PostMapping("productAdd")
    public String addProduct(HttpServletRequest request, Model model){
        String name = request.getParameter("name");

        long categoryID = Long.parseLong(request.getParameter("category.ID"));//BE CAREFUL
        long manufacturerID = Long.parseLong(request.getParameter("manufacturer.ID"));

        String description = request.getParameter("description");
        String imgLink = request.getParameter("linkToImg");


        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setLinkToImg(imgLink);
        newProduct.setDescription(description);

        productService.addNewProduct(newProduct, manufacturerID, categoryID);

        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);

        return "redirect:/productPage";
    }

    @RequestMapping(value = "/productPage/{product_id}")
    public String goToProduct(@PathVariable("product_id") String product_ID, String ID, Model model) throws IOException{
        Long id = Long.parseLong(ID);
        Optional<Product> product = productService.getAllProducts().stream().filter(product1 -> {
            return product1.getId()==id;
        }).findAny();
        if(!product.isPresent())
            throw new ProductNotFoundException();
        model.addAttribute("product",product.get());
        return "product";
    }
}

