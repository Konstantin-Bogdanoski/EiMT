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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.swing.text.html.parser.Entity;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    private long counter;
    private long counterCat;
    private long counterMan;
    private long manufacturerID;
    private long categoryID;
    private List<Product> productList = null;
    private List<Manufacturer> manufacturerList = null;
    private List<Category> categories = null;
    private Product product;

    @PostConstruct
    public void init(){
        counter = 1L;
        counterCat = 1L;
        counterMan = 1L;
        productList = new ArrayList<>();
        manufacturerList = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturerID = 1l;
        categoryID = 1l;
        product = new Product();


        Category c1 = new Category();
        c1.setID(getNextCatId());
        c1.setName("Shoes");

        Manufacturer m1 = new Manufacturer();
        m1.setID(getNextManId());
        m1.setName("Nike");

        Product p1 = new Product();
        p1.setCategory(c1);
        p1.setDescription("Nike Basketball shoes");
        p1.setId(getNextId());
        p1.setManufacturer(m1);
        p1.setName("Hyperdunk X");
        p1.setLinkToImg("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/bpuknsy73bmhmyj0agjj/hyperdunk-basketball-shoe-HlV5cq.jpg");

        productList.add(p1);
        manufacturerList.add(m1);
        categories.add(c1);

        Manufacturer m2 = new Manufacturer();
        m2.setID(getNextManId());
        m2.setName("Li-Ning");

        Product p2 = new Product();
        p2.setCategory(c1);
        p2.setDescription("Lining Basketball shoes");
        p2.setId(getNextId());
        p2.setManufacturer(m2);
        p2.setName("Way of Wade 6");
        p2.setLinkToImg("https://cdn11.bigcommerce.com/s-fqrqhjae/images/stencil/2000x2000/products/772/10029/ABAM089-30-5__35703.1522915136.jpg?c=2");

        productList.add(p2);
        manufacturerList.add(m2);


        Manufacturer m3 = new Manufacturer();
        m3.setID(getNextManId());
        m3.setName("Adidas");

        Product p3 = new Product();
        p3.setCategory(c1);
        p3.setDescription("Adidas Basketball shoes");
        p3.setId(getNextId());
        p3.setManufacturer(m3);
        p3.setName("Harden Vol.3");
        p3.setLinkToImg("https://www.hoopjordan.net/wp-content/uploads/2018/10/adidas-Harden-Vol-3-White-Black-For-Sale.jpeg.jpg");

        productList.add(p3);
        manufacturerList.add(m3);

        Product p4 = new Product();
        p4.setCategory(c1);
        p4.setDescription("Adidas Basketball shoes");
        p4.setId(getNextId());
        p4.setManufacturer(m3);
        p4.setName("AdiRose 9");
        p4.setLinkToImg("https://n1.sdlcdn.com/imgs/h/s/p/Adidas-D-ROSE-9-2018-SDL828381828-4-b6161.jpeg");

        productList.add(p4);

        Category c2 = new Category();
        c2.setID(getNextCatId());
        c2.setName("Jackets");

        Manufacturer m4 = new Manufacturer();
        m4.setName("Salomon");
        m4.setID(getNextManId());

        Product p5 = new Product();
        p5.setCategory(c2);
        p5.setManufacturer(m4);
        p5.setDescription("Salomon winter jacket");
        p5.setName("BONATTI PRO WP JKT");
        p5.setId(getNextId());
        p5.setLinkToImg("https://www.salomon.com/sites/default/files/products-images/900x900/bonatti-pro-wp-jkt-m__LC1042900.jpg");

        productList.add(p5);
        categories.add(c2);
    }

    @GetMapping("productPage")
    public String products(Model model){
        model.addAttribute("productList", productList);
        model.addAttribute("productList", productList);
        model.addAttribute("manufacturerList", manufacturerList);
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);
        return "productPage";
    }

    @PostMapping("productPage")
    public String products(HttpServletRequest request, Model model){
        model.addAttribute("productList", productList);
        model.addAttribute("productList", productList);
        model.addAttribute("manufacturerList", manufacturerList);
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);
        return "productPage";
    }

    @GetMapping("productAdd")
    public String addProduct(Model model){
        model.addAttribute("productList", productList);
        model.addAttribute("productList", productList);
        model.addAttribute("manufacturerList", manufacturerList);
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);
        return "productAdd";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @PostMapping("productAdd")
    public String addProduct(HttpServletRequest request, Model model){
        String name = request.getParameter("name");

        String categoryID = request.getParameter("category.ID");//BE CAREFUL
        Optional<Category> category = categories.stream().filter(cat -> {
            return cat.getID()==(Long.parseLong(categoryID));
        }).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        Long manufacturerID = Long.parseLong(request.getParameter("manufacturer.ID"));
        Optional<Manufacturer> manufacturer = manufacturerList.stream().filter(man -> {
           return man.getID()==manufacturerID;
        }).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }

        String description = request.getParameter("description");
        String imgLink = request.getParameter("linkToImg");
        long pID = getNextId();


        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setLinkToImg(imgLink);
        newProduct.setManufacturer(manufacturer.get());
        newProduct.setCategory(category.get());
        newProduct.setDescription(description);
        newProduct.setId(pID);

        productList.add(newProduct);
        model.addAttribute("productList", productList);
        model.addAttribute("manufacturerList", manufacturerList);
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturerID", manufacturerID);
        model.addAttribute("categoryID", categoryID);
        model.addAttribute("product", product);
        return "redirect:/productPage";
    }

    private long getNextId() {return counter++;}
    private long getNextManId() {return counterMan++;}
    private long getNextCatId() {return counterCat++;}

    @RequestMapping(value = "/productPage/{product_id}")
    public String goToProduct(@PathVariable("product_id") String product_ID, String ID, Model model) throws IOException{
        Long id = Long.parseLong(ID);
        Optional<Product> product = productList.stream().filter(product1 -> {
            return product1.getId()==id;
        }).findAny();
        if(!product.isPresent())
            throw new ProductNotFoundException();
        model.addAttribute("product",product.get());
        return "product";
    }

}

