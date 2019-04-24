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

        Product tempProduct2 = new Product();
        tempProduct2.setName("AdiRose 9");
        tempProduct2.setDescription("Adidas basketball shoe");
        tempProduct2.setLinkToImg("https://images.nikedropshipping.com/images/201807/uploaded/8a82be0e5af733fab281857470d58014.jpg");
        tempProduct2.setPrice(135);

        Manufacturer tempManufacturer2 = manufacturerService.getByName("Adidas");
        Category tempCategory2 = categoryService.getByName("Shoes");
        //END INPUT OF TEMPORARY PRODUCT


        product = new Product();
        manufacturerID = 1l;
        categoryID = 1l;

        productService.addNewProduct(tempProduct, tempManufacturer.getID(), tempCategory.getID());
        productService.addNewProduct(tempProduct2, tempManufacturer2.getID(), tempCategory2.getID());
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

    @RequestMapping(value = "/productPage/productID/{productEdit}")
    public String editProduct(@PathVariable("productEdit") String productEdit, String ID, Model model) throws IOException{
        Long id = Long.parseLong(ID);
        Optional<Product> product = productService.getAllProducts().stream().filter(product1 -> {
            return product1.getId()==id;
        }).findAny();
        if(!product.isPresent())
            throw new ProductNotFoundException();
        model.addAttribute("product",product.get());
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

        Optional<Product> product = productService.getAllProducts()
                .stream()
                .filter(v -> {
                    return v.getId() == ID;
                }).findAny();

        if(!product.isPresent())
            throw new ProductNotFoundException();

        product.get().setName(newName);
        product.get().setDescription(newDescription);
        productService.update(product.get());

        model.addAttribute("product", product);
        return "redirect:/productPage";
    }

    @DeleteMapping("/")
    public String productDelete(HttpServletRequest request) {
        Long productID = Long.parseLong(request.getParameter("productID"));
        productService.delete(productService.getById(productID));
        return "redirect:/productPage/";
    }

    @RequestMapping(value = "/productPage/{product_id}", method = RequestMethod.DELETE)
    public String productDelete(@PathVariable("product_id") String product_id){
        Long productID =  Long.parseLong(product_id);
        productService.delete(productService.getById(productID));
        return "redirect:/productPage/";
    }
}

