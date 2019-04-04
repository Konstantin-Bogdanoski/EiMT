package finki.ukim.mk.emt.konstantinb.lab01;
/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab01Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab01Application.class, args);
    }
}
