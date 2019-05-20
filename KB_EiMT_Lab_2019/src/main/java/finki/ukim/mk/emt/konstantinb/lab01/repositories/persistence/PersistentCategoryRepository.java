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
    List<Category> findAll();

    /**Delete category by ID */
    void deleteByID(@Param("ID") Long ID);

    /**Get category by ID */
    Optional<Category> findByID(@Param("ID") Long ID);

    /**Get category by NAME */
    Optional<Category> findByName(@Param("name") String name);

    /**Insert category*/
    @Transactional
    @Modifying
    Category save(Category category);
}
