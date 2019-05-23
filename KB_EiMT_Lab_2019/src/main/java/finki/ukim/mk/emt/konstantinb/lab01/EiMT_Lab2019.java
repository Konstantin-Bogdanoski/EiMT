package finki.ukim.mk.emt.konstantinb.lab01;
/**
 *
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ServletComponentScan
@SpringBootApplication
public class EiMT_Lab2019 {

    @Bean(name = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(EiMT_Lab2019.class, args);
    }

}
