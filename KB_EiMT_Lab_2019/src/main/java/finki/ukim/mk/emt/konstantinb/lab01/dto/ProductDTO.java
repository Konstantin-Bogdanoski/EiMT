package finki.ukim.mk.emt.konstantinb.lab01.dto;

import finki.ukim.mk.emt.konstantinb.lab01.models.Product;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public class ProductDTO {
    private Long id;
    private String name;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
