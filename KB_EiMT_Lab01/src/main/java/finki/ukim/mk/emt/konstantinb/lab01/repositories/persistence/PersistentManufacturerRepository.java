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
    @Query("select manufacturer from Manufacturer manufacturer")
    List<Manufacturer> getManufacturerList();

    /**Delete manufacturer by ID */
    @Query("delete from Manufacturer manufacturer where manufacturer.ID=:ID")
    void removeManufacturer(@Param("ID") Long ID);

    /**Get manufacturer by ID */
    @Query("select manufacturer from Manufacturer manufacturer where manufacturer.ID=:ID")
    Optional<Manufacturer> getByID(@Param("ID") Long ID);

    /**Get manufacturer by NAME */
    @Query("select manufacturer from Manufacturer manufacturer where manufacturer.name=:name")
    Optional<Manufacturer> getByName(@Param("name") String name);

    /**Update manufacturerName */
    @Query("update Manufacturer manufacturer set manufacturer.name=:name where manufacturer.ID=:ID")
    void updateManufacturerName(@Param("name") String name, @Param("ID") Long ID);

    /**Insert manufacturer*/
    @Transactional
    @Modifying
    @Query(value = "insert into manufacturer (manufacturer_name) values (:name)", nativeQuery = true)
    void addManufacturer(@Param("name") String name);

}
