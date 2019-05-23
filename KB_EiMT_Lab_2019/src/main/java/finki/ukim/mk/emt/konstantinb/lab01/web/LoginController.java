package finki.ukim.mk.emt.konstantinb.lab01.web;

import finki.ukim.mk.emt.konstantinb.lab01.models.User;
import finki.ukim.mk.emt.konstantinb.lab01.models.UserRole;
import finki.ukim.mk.emt.konstantinb.lab01.services.UserRoleService;
import finki.ukim.mk.emt.konstantinb.lab01.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Controller
public class LoginController {

    private UserService userService;
    private UserRoleService userRoleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserService userService, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userRoles", userRoleService.getRoles());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPOST(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Long roleID = Long.parseLong(request.getParameter("role"));

        Optional<UserRole> chosenRole = userRoleService.getById(roleID);

        if (!chosenRole.isPresent())
            throw new UsernameNotFoundException("WRONG ROLE");
        User newUser = new User();
        newUser.setUserRole(chosenRole.get());
        newUser.setUsername(username);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));

        if (userService.getUsers().stream().noneMatch(v -> {
            return newUser.equals(v);
        }))
            userService.addUser(newUser);

        return "redirect:/login";
    }
}
