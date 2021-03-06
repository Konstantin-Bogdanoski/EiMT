package finki.ukim.mk.emt.konstantinb.lab01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Product Not Found Exception")
public class ProductNotFoundException extends RuntimeException {
}
