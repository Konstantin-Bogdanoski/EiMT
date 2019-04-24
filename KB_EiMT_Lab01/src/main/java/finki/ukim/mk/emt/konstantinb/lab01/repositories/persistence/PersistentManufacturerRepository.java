package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentManufacturerRepository extends Repository<Manufacturer, Long> {
    /**Get list of all manufacturers */
    @Query("select manufacturer from Manufacturer manufacturer")
    List<Manufacturer> getAll();

    /**Delete manufacturer by ID */
    @Query("delete from Manufacturer manufacturer where manufacturer.ID=:ID")
    void deleteByID(@Param("ID") Long ID);

    /**Get manufacturer by ID */
    @Query("select manufacturer from Manufacturer manufacturer where manufacturer.ID=:ID")
    Optional<Manufacturer> getByID(@Param("ID") Long ID);

    /**Update manufacturerName */
    @Query("update Manufacturer manufacturer set manufacturer.name=:name where manufacturer.ID=:ID")
    void updateManufacturerName(@Param("name") String name, @Param("ID") Long ID);

}
