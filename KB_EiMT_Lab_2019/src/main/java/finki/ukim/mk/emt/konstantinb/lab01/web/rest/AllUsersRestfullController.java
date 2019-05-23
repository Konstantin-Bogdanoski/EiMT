
package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */

import finki.ukim.mk.emt.konstantinb.lab01.models.User;
import finki.ukim.mk.emt.konstantinb.lab01.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class AllUsersRestfullController {
    private UserService userService;

    public AllUsersRestfullController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }
}

