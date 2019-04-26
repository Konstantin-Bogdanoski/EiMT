package finki.ukim.mk.emt.konstantinb.lab01.web;
/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.*;
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
import java.util.*;

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


        //TEMPORARY PRODUCT, SO THE LIST ISN'T EMPTY
        Product tempProduct = new Product();
        tempProduct.setName("Hyperdunk X");
        tempProduct.setDescription("Nike basketball shoe");
        tempProduct.setLinkToImg("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/bpuknsy73bmhmyj0agjj/hyperdunk-basketball-shoe-HlV5cq.jpg");
        tempProduct.setPrice(180);

        Manufacturer tempManufacturer = manufacturerService.getByName("Nike");
        Category tempCategory = categoryService.getByName("Shoes");

        if(!productService.getAllProducts().stream().anyMatch(product1 -> {
            return product1.equals(tempProduct);
        }))
            productService.addNewProduct(tempProduct, tempManufacturer.getID(), tempCategory.getID());

        Product tempProduct2 = new Product();
        tempProduct2.setName("AdiRose 9");
        tempProduct2.setDescription("Adidas basketball shoe");
        tempProduct2.setLinkToImg("https://images.nikedropshipping.com/images/201807/uploaded/8a82be0e5af733fab281857470d58014.jpg");
        tempProduct2.setPrice(135);

        Manufacturer tempManufacturer2 = manufacturerService.getByName("Adidas");
        Category tempCategory2 = categoryService.getByName("Shoes");
        if(!productService.getAllProducts().stream().anyMatch(product1 -> {
            return product1.equals(tempProduct2);
        }))
            productService.addNewProduct(tempProduct2, tempManufacturer2.getID(), tempCategory2.getID());

        //END INPUT OF TEMPORARY PRODUCT

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
        Double price = Double.parseDouble(request.getParameter("price"));

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setLinkToImg(imgLink);
        newProduct.setDescription(description);
        if(price <= 0)
            throw new IllegalArgumentException("Illegal price value: price = " + price.toString());
        newProduct.setPrice(price);

        productService.addNewProduct(newProduct, manufacturerID, categoryID);

        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);

        return "redirect:/productPage";
    }

    @GetMapping("categoryAdd")
    public String addCategory(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("category", new Category());
        return "categoryAdd";
    }

    @PostMapping("categoryAdd")
    public String addCategory(HttpServletRequest request, Model model) {
        String categoryName = request.getParameter("name");
        if (categoryService.getCategories().stream().anyMatch(cat -> {
            return cat.getName().equals(categoryName);
        }))
            throw new CategoryAlreadyExistsException();
        categoryService.addNewCategory(categoryName);
        return "redirect:/productPage";
    }

    @GetMapping("manufacturerAdd")
    public String addManufacturer(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturerAdd";
    }

    @PostMapping("manufacturerAdd")
    public String addManufacturer(HttpServletRequest request, Model model) {
        String manufacturerName = request.getParameter("name");
        if (manufacturerService.getAllManufacturers().stream().anyMatch(man -> {
            return man.getName().equals(manufacturerName);
        }))
            throw new ManufacturerAlreadyExistsException();
        manufacturerService.addNewManufacturer(manufacturerName);
        return "redirect:/productPage";
    }

    @RequestMapping(value = "/productPage/{product_id}")
    public String goToProduct(@PathVariable("product_id") String product_ID, String ID, Model model) throws IOException{
        Long id = Long.parseLong(ID);
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "/productPage/productID/{productEdit}")
    public String editProduct(@PathVariable("productEdit") String productEdit, String ID, Model model) throws IOException{
        Long id = Long.parseLong(ID);
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "productEdit";
    }

    @GetMapping("productEdit")
    public String getProductEdit(Model model){
        model.addAttribute("product", product);
        return "productEdit";
    }

    @PostMapping("productEdit")
    public String productEdit(HttpServletRequest request, Model model){
        String newName = request.getParameter("name");
        String newDescription = request.getParameter("description");
        Long ID = Long.parseLong(request.getParameter("id"));

        Product product = productService.getById(ID);
        product.setName(newName);
        product.setDescription(newDescription);
        productService.update(product);

        model.addAttribute("product", product);
        return "redirect:/productPage";
    }

    @DeleteMapping("/")
    public String productDelete(HttpServletRequest request) {
        Long productID = Long.parseLong(request.getParameter("productID"));
        productService.deleteProduct(productService.getById(productID));
        return "redirect:/productPage/";
    }
}

