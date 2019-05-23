package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import finki.ukim.mk.emt.konstantinb.lab01.models.UserRole;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence.PersistentUserRoleRepository;
import finki.ukim.mk.emt.konstantinb.lab01.services.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private PersistentUserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(PersistentUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> getRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getByName(String name) {
        return userRoleRepository.findByName(name);
    }

    @Override
    public Optional<UserRole> getById(Long ID) {
        return userRoleRepository.findByID(ID);
    }

}
