package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
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
public interface PersistentManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    /**Get list of all manufacturers */
    List<Manufacturer> findAll();

    /**Delete manufacturer by ID */
    void deleteByID(Long ID);

    /**Get manufacturer by ID */
    Optional<Manufacturer> findByID(@Param("ID") Long ID);

    /**Get manufacturer by NAME */
    Optional<Manufacturer> findByName(@Param("name") String name);

    /**Insert manufacturer*/
    @Transactional
    @Modifying
    Manufacturer save(Manufacturer manufacturer);

}
