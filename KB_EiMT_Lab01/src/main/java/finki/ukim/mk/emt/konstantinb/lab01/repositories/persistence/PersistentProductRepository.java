package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentProductRepository extends Repository<Product, Long> {

    /** Get list of all products*/
    @Query("select product from Product product")
    List<Product> getAll();

    /**Delete product from productList */
    @Query("delete from Product product where product=:product")
    void deleteById(@Param("product") Product product);

    /**Get product by ID */
    @Query("select product from Product product where product.id=:id")
    Optional<Product> getById(@Param("product") Product product);

    /**Get list of products by CATEGORY */
    @Query("select product from Product product where product.category=:category")
    List<Product> getByCategory(@Param("category") Category category);

    /**Get list of products by MANUFACTURER */
    @Query("select product from Product product where product.manufacturer=:manufacturer")
    List<Product> getByManufacturer(@Param("manufacturer") Manufacturer manufacturer);

    /**Get list of products by CATEGORY && MANUFACTURER */
    @Query("select product from Product product where product.manufacturer=:manufacturer and product.category=:category")
    List<Product> getAllByCategoryAndManufacturer(@Param("manufacturer") Manufacturer manufacturer, @Param("category") Category category);

    /**Count number of products */
    @Query("select count(product.id) from Product product")
    Long countAll();


    /**Get products by IDs by list of IDs */
    @Query("select product from Product product where product.id in :idList")
    List<Product> getAllById(@Param("idList") List<Long> idList);

    /**Get total price of all products */
    @Query("select sum(product.price) from Product product")
    Double getPrice();

    /**Update productName */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Product product set product.name=:name where product.id=:id")
    void updateProductName(@Param("productID") Long id, @Param("productName") String name);

    /**Update productDescription */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Product product set product.description=:description where product.id=:id")
    void updateProductDescription(@Param("id") Long id, @Param("description") String description);
}
