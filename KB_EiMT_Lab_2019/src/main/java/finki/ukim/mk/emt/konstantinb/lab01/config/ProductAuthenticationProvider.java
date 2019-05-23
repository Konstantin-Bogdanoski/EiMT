package finki.ukim.mk.emt.konstantinb.lab01.config;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.UserNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.User;
import finki.ukim.mk.emt.konstantinb.lab01.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Component
public class ProductAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    public ProductAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(username) || "".equals(password)) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username invalid");
        }

        Boolean passwordsEqual = passwordEncoder.matches(password, user.getPassword());
        if (!passwordsEqual) {
            throw new UsernameNotFoundException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
