package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentCategoryRepository extends Repository<Category,Long> {

    /**Get list of all categories */
    @Query("select category from Category category")
    List<Category> getAll();

    /**Delete category by ID */
    @Query("delete from Category category where category.ID=:ID")
    void deleteByID(@Param("ID") Long ID);

    /**Get category by ID */
    @Query("select category from Category category where category.ID=:ID")
    Optional<Category> getByID(@Param("ID") Long ID);

    /**Update categoryName */
    @Query("update Category category set category.name=:name where category.ID=:ID")
    void updateCategoryName(@Param("name") String name, @Param("ID") Long ID);

    /**Update categoryProductList */
    @Query("update Category category set category.products=:products where category.ID=:ID")
    void updateCategoryProducts(@Param("productList") List<Product> products, @Param("ID") Long ID);


}
