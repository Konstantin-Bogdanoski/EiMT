package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.UserNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.User;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface UserService {
    void addUser(User user);

    void removeUser(User user);

    List<User> getUsers();

    User findByUsername(String username) throws UserNotFoundException;
}
