package finki.ukim.mk.emt.konstantinb.lab01.web;

import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
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
        productList = new ArrayList<Product>();
        manufacturerList = new ArrayList<Manufacturer>();
        categories = new ArrayList<Category>();

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
    }

    @GetMapping("/")
    public String products(Model model){
        model.addAttribute("productList", productList);
        return "products";
    }

    @PostMapping("/")
    public String addProduct(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        long categoryID = Long.parseLong(request.getParameter("category"));//BE CAREFUL
        String categoryName = request.getParameter("catName");
        long manufacturerID = Long.parseLong(request.getParameter("manufacturer"));
        String manufacturerName = request.getParameter("manName");
        String description = request.getParameter("description");
        String imgLink = request.getParameter("img-link");
        long pID = getNextId();

        Category category = new Category();
        category.setID(categoryID);
        category.setName(categoryName);

        if(!categories.contains(category)) // ADD NEW CATEGORY
            categories.add(category);


        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setID(manufacturerID);
        manufacturer.setName(manufacturerName);

        if(!manufacturerList.contains(manufacturer))
            manufacturerList.add(manufacturer);


        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setLinkToImg(imgLink);
        newProduct.setManufacturer(manufacturer);
        newProduct.setCategory(category);
        newProduct.setDescription(description);
        newProduct.setId(pID);

        productList.add(newProduct);
        model.addAttribute("productList", productList);
        return "products";
    }

    private long getNextId() {return counter++;}
    private long getNextManId() {return counterMan++;}
    private long getNextCatId() {return counterCat++;}
}

