package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentUserRepository extends JpaRepository<User, String> {
    /**
     * Get all users
     */
    List<User> findAll();

    /**
     * Get user by username
     */
    User findByUsername(@Param("username") String username);

    /**
     * Add/Update user
     */
    @Transactional
    @Modifying
    User save(User user);

    /**
     * Remove user
     */
    @Transactional
    @Modifying
    void delete(User user);
}
