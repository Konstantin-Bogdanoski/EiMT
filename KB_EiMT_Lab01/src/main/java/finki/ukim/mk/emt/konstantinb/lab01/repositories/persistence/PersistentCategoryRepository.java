package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface PersistentCategoryRepository extends JpaRepository<Category,Long> {

    /**Get list of all categories */
    @Query("select category from Category category")
    List<Category> getCategories();

    /**Delete category by ID */
    @Query("delete from Category category where category.ID=:ID")
    void deleteCategory(@Param("ID") Long ID);

    /**Get category by ID */
    @Query("select category from Category category where category.ID=:ID")
    Optional<Category> getByID(@Param("ID") Long ID);

    /**Get category by NAME */
    @Query("select category from Category category where category.name=:name")
    Optional<Category> getByName(@Param("name") String name);

    /**Update categoryName */
    @Query("update Category category set category.name=:name where category.ID=:ID")
    void updateCategoryName(@Param("name") String name, @Param("ID") Long ID);

    /**Insert category*/
    @Transactional
    @Modifying
    @Query(value = "insert into category (category_name) values (:name)", nativeQuery = true)
    void addCategory(@Param("name") String name);

    /**Update categoryProductList */
    /*@Query("update Category category set category.products=:products where category.ID=:ID")
    void updateCategoryProducts(@Param("products") List<Product> products, @Param("ID") Long ID);*/


}
