package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.models.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface UserRoleService {
    List<UserRole> getRoles();

    UserRole getByName(String name);

    Optional<UserRole> getById(Long ID);
}
