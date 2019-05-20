package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentProductRepository extends JpaRepository<Product, Long> {

    /** Get list of all products*/
    List<Product> findAll();

    @Transactional
    @Modifying
    /**Delete product from productList */
    void deleteById(@Param("id") Long id);

    /**Get product by ID */
    Optional<Product> findById(@Param("id") Long id);

    /**Get product by NAME */
    Optional<Product> findByName(@Param("name") String name);


    /**Get list of products by CATEGORY */
    List<Product> findAllByCategory(@Param("category") Category category);


    /**Get SUM of products by CATEGORY*/
    @Query("select sum(product.price) from Product product where product.category=:category")
    Double getPrice(@Param("category") Category category);

    /**Get list of products by MANUFACTURER */
    List<Product> findAllByManufacturer(@Param("manufacturer") Manufacturer manufacturer);

    /**Get list of products by CATEGORY && MANUFACTURER */
    List<Product> findAllByCategoryAndManufacturer(@Param("manufacturer") Manufacturer manufacturer, @Param("category") Category category);

    /**Count number of products */
    long count();


    /**Get products by IDs by list of IDs */
    List<Product> findAllById(@Param("idList") List<Long> idList);

    /**Get total price of all products */
    @Query("select sum(product.price) from Product product")
    Double getPrice();

    /**Insert new Product*/
    @Transactional
    @Modifying
    Product save(Product product);
}
