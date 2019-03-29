package finki.ukim.mk.emt.konstantinb.lab01.web;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*Why use RestController instead of Controller??
While using Controller we get an error, the "server" thinks
that there is an infinite loop while trying to connect to the page "/products"*/
@Controller
@RequestMapping("/")
public class ProductController {
    private long counter;
    private long counterCat;
    private long counterMan;
    private List<Product> productList = null;
    private List<Manufacturer> manufacturerList = null;
    private List<Category> categories = null;

    @PostConstruct
    public void init(){
        counter = 1L;
        counterCat = 1L;
        counterMan = 1L;
        productList = new ArrayList<>();
        manufacturerList = new ArrayList<>();
        categories = new ArrayList<>();

        Category c1 = new Category();
        c1.setID(getNextCatId());
        c1.setName("Sport");

        Manufacturer m1 = new Manufacturer();
        m1.setID(getNextManId());
        m1.setName("Nike");

        Product p1 = new Product();
        p1.setCategory(c1);
        p1.setDescription("Nike Basketball shoes");
        p1.setId(getNextId());
        p1.setManufacturer(m1);
        p1.setName("Hyperdunk");
        p1.setLinkToImg("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/bpuknsy73bmhmyj0agjj/hyperdunk-basketball-shoe-HlV5cq.jpg");

        productList.add(p1);
        manufacturerList.add(m1);
        categories.add(c1);

        Manufacturer m2 = new Manufacturer();
        m2.setID(getNextManId());
        m2.setName("Adidas");

        Product p2 = new Product();
        p2.setCategory(c1);
        p2.setDescription("Adidas Basketball shoes");
        p2.setId(getNextId());
        p2.setManufacturer(m2);
        p2.setName("Harden Vol.3");
        p2.setLinkToImg("https://www.hoopjordan.net/wp-content/uploads/2018/10/adidas-Harden-Vol-3-White-Black-For-Sale.jpeg.jpg");

        productList.add(p2);
        manufacturerList.add(m2);
    }

    @GetMapping("productPage")
    public String products(Model model){
        model.addAttribute("productList", productList);
        return "productPage";
    }

    @GetMapping("productAdd")
    public String addProduct(Model model){
        model.addAttribute("productList", productList);
        return "productAdd";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @PostMapping("productAdd")
    public String addProduct(HttpServletRequest request, Model model){
        String name = request.getParameter("name");

        Long categoryID = Long.parseLong(request.getParameter("categoryID"));//BE CAREFUL
        Optional<Category> category = categories.stream().filter(cat -> {
            return cat.getID()==(categoryID);
        }).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        Long manufacturerID = Long.parseLong(request.getParameter("manufacturerID"));
        Optional<Manufacturer> manufacturer = manufacturerList.stream().filter(man -> {
           return man.getID()==manufacturerID;
        }).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }

        String description = request.getParameter("description");
        String imgLink = request.getParameter("img-link");
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
        return "productAdd";
    }

    private long getNextId() {return counter++;}
    private long getNextManId() {return counterMan++;}
    private long getNextCatId() {return counterCat++;}
}

