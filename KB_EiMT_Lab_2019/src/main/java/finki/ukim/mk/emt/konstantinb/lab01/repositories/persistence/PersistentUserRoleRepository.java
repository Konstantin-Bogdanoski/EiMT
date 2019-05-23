package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import com.sun.xml.internal.bind.v2.model.core.ID;
import finki.ukim.mk.emt.konstantinb.lab01.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentUserRoleRepository extends JpaRepository<UserRole, Long> {
    /**
     * Get all roles
     */
    List<UserRole> findAll();

    /**
     * Get role by Name
     */
    UserRole findByName(@Param("name") String name);

    /**
     * Get role by ID
     */
    Optional<UserRole> findByID(@Param("ID") Long id);
}
